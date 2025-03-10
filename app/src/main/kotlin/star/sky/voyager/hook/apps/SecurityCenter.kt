package star.sky.voyager.hook.apps

import de.robv.android.xposed.callbacks.XC_LoadPackage
import star.sky.voyager.hook.hooks.securitycenter.AppDisable
import star.sky.voyager.hook.hooks.securitycenter.LockOneHundred
import star.sky.voyager.hook.hooks.securitycenter.OpenByDefaultSetting
import star.sky.voyager.hook.hooks.securitycenter.RemoveConversationBubbleSettingsRestriction
import star.sky.voyager.hook.hooks.securitycenter.RemoveOpenAppConfirmationPopup
import star.sky.voyager.hook.hooks.securitycenter.RemoveReport
import star.sky.voyager.hook.hooks.securitycenter.ShowBatteryTemperature
import star.sky.voyager.hook.hooks.securitycenter.SkipWaitingTime
import star.sky.voyager.utils.init.AppRegister

object SecurityCenter : AppRegister() {
    override val packageName: String = "com.miui.securitycenter"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        autoInitHooks(
            lpparam,
            SkipWaitingTime, // 跳过 5/10 秒警告时间
            RemoveOpenAppConfirmationPopup, // 移除打开应用弹窗
            LockOneHundred, // 手机管家锁定100分
            ShowBatteryTemperature, // 电池页面显示当前温度
            OpenByDefaultSetting, // 将应用详情中“消除默认操作”改为“默认打开”设置
            RemoveReport, // 移除应用详情界面的举报
            AppDisable, // 在应用详情界面显示停用App
            RemoveConversationBubbleSettingsRestriction, // 去除气泡通知应用限制
        )
    }
}