package star.sky.voyager.hook.apps

import de.robv.android.xposed.callbacks.XC_LoadPackage
import star.sky.voyager.hook.hooks.misettings.AllFps
import star.sky.voyager.hook.hooks.misettings.CustomRefreshRateS
import star.sky.voyager.hook.hooks.misettings.DcFps
import star.sky.voyager.utils.init.AppRegister

object MiSettings : AppRegister() {
    override val packageName: String = "com.xiaomi.misettings"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        autoInitHooks(
            lpparam,
            CustomRefreshRateS, // 自定义高刷新率应用
            DcFps, // 高刷DC同开
            AllFps, // 显示全部刷新率档位
        )
    }
}