package com.yingyang.sacn

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

class MainActivity : FlutterActivity() {

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        ScanChannel(flutterEngine.dartExecutor.binaryMessenger, context)  // 实例化通道
    }
}
