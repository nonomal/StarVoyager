package star.sky.voyager.utils.init

import com.github.kyuubiran.ezxhelper.EzXHelper
import com.github.kyuubiran.ezxhelper.EzXHelper.initHandleLoadPackage
import com.github.kyuubiran.ezxhelper.EzXHelper.setLogTag
import com.github.kyuubiran.ezxhelper.EzXHelper.setToastTag
import com.github.kyuubiran.ezxhelper.Log
import com.github.kyuubiran.ezxhelper.LogExtensions.logexIfThrow
import de.robv.android.xposed.IXposedHookInitPackageResources
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.callbacks.XC_InitPackageResources
import de.robv.android.xposed.callbacks.XC_LoadPackage
import star.sky.voyager.utils.yife.DexKit.closeDexKit
import star.sky.voyager.utils.yife.DexKit.initDexKit

abstract class EasyXposedInit : IXposedHookLoadPackage, IXposedHookZygoteInit, IXposedHookInitPackageResources {

    private lateinit var packageParam: XC_LoadPackage.LoadPackageParam
    private lateinit var packageResourcesParam: XC_InitPackageResources.InitPackageResourcesParam
    abstract val registeredApp: List<AppRegister>
    private val TAG = "Voyager"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {

        packageParam = lpparam!!
        registeredApp.forEach { app ->
            if (app.packageName == lpparam.packageName) {
                EzXHelper.apply {
                    if (lpparam.packageName != "android") {
                        initDexKit(lpparam)
                    }
                    initHandleLoadPackage(lpparam)
                    setLogTag(TAG)
                    setToastTag(TAG)
                }
                runCatching { app.handleLoadPackage(lpparam) }.logexIfThrow("Failed call handleLoadPackage, package: ${app.packageName}")
            }
        }
        if (lpparam.packageName != "android") {
            closeDexKit()
        }
    }

    override fun initZygote(startupParam: IXposedHookZygoteInit.StartupParam?) {
        EzXHelper.initZygote(startupParam!!)
    }

    override fun handleInitPackageResources(resparam: XC_InitPackageResources.InitPackageResourcesParam?) {
        packageResourcesParam = resparam!!
        registeredApp.forEach { app ->
            if(app.packageName == resparam.packageName) {
                runCatching { app.handleInitPackageResources(resparam) }.logexIfThrow("Failed call handleInitPackageResources, package: ${app.packageName}")
            }
        }
    }
}