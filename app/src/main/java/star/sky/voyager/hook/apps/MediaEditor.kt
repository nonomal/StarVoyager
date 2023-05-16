package star.sky.voyager.hook.apps

import de.robv.android.xposed.callbacks.XC_LoadPackage
import star.sky.voyager.hook.hooks.mediaeditor.FilterManager
import star.sky.voyager.hook.hooks.mediaeditor.UnlockUnlimitedCropping
import star.sky.voyager.utils.init.AppRegister

object MediaEditor : AppRegister() {
    override val packageName: String = "com.miui.mediaeditor"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        autoInitHooks(
            lpparam,
            FilterManager,
            UnlockUnlimitedCropping,
        )
    }
}