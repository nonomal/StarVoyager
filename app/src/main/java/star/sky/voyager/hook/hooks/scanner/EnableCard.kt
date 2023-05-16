package star.sky.voyager.hook.hooks.scanner

import com.github.kyuubiran.ezxhelper.ClassUtils.loadClass
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import star.sky.voyager.utils.init.HookRegister
import star.sky.voyager.utils.key.hasEnable

object EnableCard : HookRegister() {
    override fun init() = hasEnable("enable_card") {
        val qwq = loadClass("com.xiaomi.scanner.settings.FeatureManager")
        qwq.methodFinder().first {
            name == "isAddBusinessCard"
        }.createHook {
            before {
                it.result = true
            }
        }
        qwq.methodFinder().first {
            name == "isBusinessCardModuleAvailable"
        }.createHook {
            before {
                it.result = true
            }
        }
    }
}