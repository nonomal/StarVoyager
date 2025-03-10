package star.sky.voyager.hook.hooks.android

import com.github.kyuubiran.ezxhelper.ClassUtils.loadClass
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import star.sky.voyager.utils.init.HookRegister
import star.sky.voyager.utils.key.hasEnable

object DeleteOnPostNotification : HookRegister() {
    override fun init() = hasEnable("delete_on_post_notification") {
        loadClass("com.android.server.wm.AlertWindowNotification").methodFinder().first {
            name == "onPostNotification"
        }.createHook {
            before {
                it.result = null
            }
        }
    }
}