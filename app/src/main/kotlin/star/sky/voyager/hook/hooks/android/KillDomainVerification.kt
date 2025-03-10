package star.sky.voyager.hook.hooks.android

import android.content.Intent
import com.github.kyuubiran.ezxhelper.ClassUtils.loadClass
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.MemberExtensions.paramCount
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import star.sky.voyager.utils.init.HookRegister
import star.sky.voyager.utils.key.hasEnable

object KillDomainVerification : HookRegister() {
    override fun init() = hasEnable("kill_domain_verification") {
        loadClass("com.android.server.pm.verify.domain.DomainVerificationUtils").methodFinder()
            .first {
                name == "isDomainVerificationIntent" &&
                        paramCount == 2 &&
                        parameterTypes[0] == Intent::class.java &&
                        parameterTypes[1] == Long::class.java
            }.createHook {
            returnConstant(false)
        }
    }
}