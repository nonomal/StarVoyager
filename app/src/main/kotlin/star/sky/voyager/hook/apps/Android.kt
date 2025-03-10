package star.sky.voyager.hook.apps

import de.robv.android.xposed.callbacks.XC_LoadPackage
import star.sky.voyager.hook.hooks.android.AllowUntrustedTouches
import star.sky.voyager.hook.hooks.android.DeleteOnPostNotification
import star.sky.voyager.hook.hooks.android.Disable72hVerify
import star.sky.voyager.hook.hooks.android.DisableFlagSecure
import star.sky.voyager.hook.hooks.android.DisableFlagSecureK
import star.sky.voyager.hook.hooks.android.DoNotClearAppPlusA
import star.sky.voyager.hook.hooks.android.KillDomainVerification
import star.sky.voyager.hook.hooks.android.MaxFreeFormA
import star.sky.voyager.hook.hooks.android.MaxWallpaperScale
import star.sky.voyager.hook.hooks.android.SystemPropertiesHook
import star.sky.voyager.hook.hooks.corepatch.CorePatchMainHook
import star.sky.voyager.hook.hooks.newmaxmipad.DisableFixedOrientation
import star.sky.voyager.hook.hooks.newmaxmipad.IgnoreStylusKeyGesture
import star.sky.voyager.hook.hooks.newmaxmipad.NoMagicPointer
import star.sky.voyager.hook.hooks.newmaxmipad.RemoveStylusBluetoothRestriction
import star.sky.voyager.hook.hooks.newmaxmipad.RestoreEsc
import star.sky.voyager.hook.hooks.newmaxmipad.SetGestureNeedFingerNumTo4
import star.sky.voyager.utils.init.AppRegister

object Android : AppRegister() {
    override val packageName: String = "android"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        CorePatchMainHook().handleLoadPackage(lpparam) // 核心破解
        DisableFlagSecureK().handleLoadPackage(lpparam) // 允许截图
        autoInitHooks(
            lpparam,
            DisableFlagSecure, // 允许截图
            DeleteOnPostNotification, // 移除上层显示通知 // 强制使用小窗
            MaxWallpaperScale, // 壁纸缩放比例
            AllowUntrustedTouches, // 允许不受信任的触摸
            KillDomainVerification, // 禁用域验证
            MaxFreeFormA, // 解锁小窗数量限制
            Disable72hVerify, // 禁用每 72h 验证锁屏密码
            SystemPropertiesHook, // 媒体音量阶数
            DoNotClearAppPlusA, // 更激进的防止杀死后台应用
            // max mi pad
            DisableFixedOrientation, // 禁用固定屏幕方向
            IgnoreStylusKeyGesture, // 忽略触控笔按键手势
            NoMagicPointer, // 关闭Magic Pointer
            RemoveStylusBluetoothRestriction, // 去除触控笔蓝牙限制
            RestoreEsc, // 恢复ESC键功能
            SetGestureNeedFingerNumTo4, // 交换手势所需的手指数量-Android部分
            // max mi pad
        )
    }
}