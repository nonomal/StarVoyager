package star.sky.voyager.hook.hooks.gallery

import com.github.kyuubiran.ezxhelper.ClassUtils.loadClass
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import star.sky.voyager.utils.init.HookRegister
import star.sky.voyager.utils.key.hasEnable

object EnableVideoPost : HookRegister() {
    override fun init() = hasEnable("enable_video_post") {
        loadClass("com.miui.mediaeditor.api.MediaEditorApiHelper").methodFinder().first {
            name == "isVideoPostAvailable"
        }.createHook {
            after {
                it.result = true
            }
        }
    }
}