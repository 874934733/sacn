package com.yingyang.sacn

import android.content.Context
import android.content.Intent
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import kotlin.math.pow

class ScanChannel(flutterEngine: BinaryMessenger, context: Context) :
    MethodChannel.MethodCallHandler {

    private val batteryChannelName = "com.yingyang.sacn/shell"
    private var channel: MethodChannel? = null
    private var mContext: Context? = null

    init {
        channel = MethodChannel(flutterEngine, batteryChannelName)
        channel!!.setMethodCallHandler(this)
        mContext = context
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        if (call.method == "isTablet") {
            isTablet(result, mContext!!)
        } else if (call.method == "hideAllNavigation") {
            hideAllNavigation(mContext!!)
        } else {
            result.notImplemented()
        }
    }

    fun showAllNavigation(result: MethodChannel.Result?, context: Context) {
        Log.e("wpp", "---------------------------------------------------666")
        val intent1 = Intent("com.android.action.SHOW_STATUS_BAR")
        context.sendBroadcast(intent1)
        val intent2 = Intent("com.android.action.SHOW_NAVIGATION_BAR")
        context.sendBroadcast(intent2)
        val intent3 = Intent("com.android.action.SHOW_FLOAT_BACK")
        context.sendBroadcast(intent3)
        result?.success(0)
    }

    fun hideAllNavigation(context: Context) {
        Log.e("wpp", "---------------------------------------------------999")
        val intent1 = Intent("com.android.action.HIDE_STATUS_BAR")
        context.sendBroadcast(intent1)
        val intent2 = Intent("com.android.action.HIDE_NAVIGATION_BAR")
        context.sendBroadcast(intent2)
        val intent3 = Intent("com.android.action.HIDE_FLOAT_BACK")
        context.sendBroadcast(intent3)
    }

    /**
     * 判断是否为平板
     */
    private fun isTablet(result: MethodChannel.Result?, context: Context) {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.getDefaultDisplay()
        val dm = DisplayMetrics()
        display.getMetrics(dm)
        val x = (dm.widthPixels.toDouble() / dm.xdpi).pow(2)
        val y = (dm.heightPixels.toDouble() / dm.ydpi).pow(2)
        // 屏幕尺寸
        val screenInches = Math.sqrt(x + y)
        // 大于7尺寸则为Pad
        result?.success(screenInches >= 7.0)
    }


}