package star.sky.voyager.hook.hooks.packageinstaller

import com.github.kyuubiran.ezxhelper.ClassUtils.loadClassOrNull
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHooks
import com.github.kyuubiran.ezxhelper.finders.FieldFinder.`-Static`.fieldFinder
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import star.sky.voyager.utils.init.HookRegister
import star.sky.voyager.utils.key.hasEnable

object DisableSafeModelTip : HookRegister() {
    override fun init() = hasEnable("Disable_Safe_Model_Tip") {
        try {
            loadClassOrNull("com.miui.packageInstaller.model.ApkInfo")?.methodFinder()?.first {
                name == "getSystemApp"
            }?.createHook {
                returnConstant(true)
            }
            val InstallProgressActivityClass =
                loadClassOrNull("com.miui.packageInstaller.InstallProgressActivity")
            InstallProgressActivityClass?.methodFinder()
                ?.first {
                    name == "g0"
                }?.createHook {
                    returnConstant(false)
                }
            InstallProgressActivityClass?.methodFinder()
                ?.first {
                    name == "Q1"
                }?.createHook {
                    before {
                        it.result = null
                    }
                }
            InstallProgressActivityClass?.methodFinder()
                ?.filter {
                    true
                }?.toList()?.createHooks {
                    after { param ->
                        param.thisObject.javaClass.fieldFinder().first {
                            type == Boolean::class.java
                        }.setBoolean(param.thisObject, false)
                    }
                }
        } catch (_: Throwable) {

        }
    }
}