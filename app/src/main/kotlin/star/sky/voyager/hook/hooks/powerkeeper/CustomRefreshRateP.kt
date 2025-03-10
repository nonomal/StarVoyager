package star.sky.voyager.hook.hooks.powerkeeper

import com.github.kyuubiran.ezxhelper.ClassUtils.loadClass
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.MemberExtensions.paramCount
import com.github.kyuubiran.ezxhelper.ObjectUtils.setObject
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import star.sky.voyager.utils.init.HookRegister
import star.sky.voyager.utils.key.hasEnable

object CustomRefreshRateP : HookRegister() {
    override fun init() = hasEnable("custom_refresh_rate") {
        loadClass("com.miui.powerkeeper.statemachine.DisplayFrameSetting").methodFinder().first {
            name == "parseCustomModeSwitchFromDb" && paramCount == 1 && parameterTypes[0] == String::class.java
        }.createHook {
            before {
//                setObject(it.thisObject, "fucSwitch", true)
                setObject(it.thisObject, "mIsCustomFpsSwitch", "true")
//                val qwq = getObjectOrNull(it.thisObject, "mIsCustomFpsSwitch")
//                Log.i("hook mIsCustomFpsSwitch success, its:$qwq")
            }
        }
    }
}