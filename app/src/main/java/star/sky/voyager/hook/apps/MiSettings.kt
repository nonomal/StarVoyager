package star.sky.voyager.hook.apps

import de.robv.android.xposed.callbacks.XC_LoadPackage
import star.sky.voyager.hook.hooks.misettings.CustomRefreshRateS
import star.sky.voyager.utils.init.AppRegister

object MiSettings : AppRegister() {
    override val packageName: String = "com.xiaomi.misettings"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        autoInitHooks(
            lpparam,
            CustomRefreshRateS,
        )
    }
}