package star.sky.voyager.hook.hooks.home

import com.github.kyuubiran.ezxhelper.ClassUtils.loadClass
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import star.sky.voyager.utils.init.HookRegister
import star.sky.voyager.utils.key.XSPUtils.getInt
import star.sky.voyager.utils.key.hasEnable

object AlwaysBlurWallpaper : HookRegister() {
    override fun init() = hasEnable("home_blur_wallpaper") {
        val value = getInt("home_blur_radius", 100)
        loadClass("com.miui.home.launcher.common.BlurUtils").methodFinder().first {
            name == "fastBlur" && parameterCount == 4
        }.createHook {
            before {
                it.args[0] = value.toFloat() / 100
                it.args[2] = true
            }
        }
    }
}