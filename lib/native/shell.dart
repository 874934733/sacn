import 'package:flutter/services.dart';

class Shell {
  static const platform = MethodChannel('com.yingyang.sacn/shell');

  Future<bool> isTablet() async {
    try {
      return await platform.invokeMethod('isTablet');
    } on PlatformException {
      return false;
    }
  }
}
