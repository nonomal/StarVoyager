package star.sky.voyager.hook.hooks.systemui

import android.view.View
import android.widget.LinearLayout
import com.github.kyuubiran.ezxhelper.ClassUtils.loadClass
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import star.sky.voyager.utils.api.getObjectField
import star.sky.voyager.utils.init.HookRegister
import star.sky.voyager.utils.key.hasEnable

object RemoveLockScreenCamera : HookRegister() {
    override fun init() = hasEnable("remove_lock_screen_camera") {
        //屏蔽右下角组件显示
        loadClass("com.android.systemui.statusbar.phone.KeyguardBottomAreaView").methodFinder()
            .first {
                name == "onFinishInflate"
            }.createHook {
            after {
                (it.thisObject.getObjectField("mRightAffordanceViewLayout") as LinearLayout).visibility =
                    View.GONE
            }
        }

        //屏蔽滑动撞墙动画
        loadClass("com.android.keyguard.KeyguardMoveRightController").methodFinder().first {
            name == "onTouchMove" && parameterCount == 2
        }.createHook {
            before {
                it.result = false
            }
        }
        loadClass("com.android.keyguard.KeyguardMoveRightController").methodFinder().first {
            name == "reset"
        }.createHook {
            before {
                it.result = null
            }
        }
    }
}