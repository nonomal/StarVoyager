package star.sky.voyager.activity.pages.apps

import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import star.sky.voyager.R

@BMPage("scope_mi_smart_hub", "Smart Hub", hideMenu = false)
class SmartHubPage : BasePage() {
    override fun onCreate() {
        TitleText(textId = R.string.scope_mi_share)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.No_Auto_Turn_Off,
                tipsId = R.string.No_Auto_Turn_Off_summary
            ), SwitchV("No_Auto_Turn_Off")
        )
        Line()
        TitleText(textId = R.string.scope_cast)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.force_support_send_app,
            ), SwitchV("force_support_send_app")
        )
        Line()
        TitleText(textId = R.string.scope_aod)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.unlock_always_on_display_time,
            ), SwitchV("unlock_always_on_display_time")
        )
        Line()
        TitleText(textId = R.string.scope_wallpaper)
        TextSummaryWithSwitch(
            TextSummaryV(
                textId = R.string.unlock_super_wallpaper,
            ), SwitchV("unlock_super_wallpaper")
        )
    }
}