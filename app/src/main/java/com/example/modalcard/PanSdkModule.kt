package com.example.modalcard

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class PanSdkModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    override fun getName(): String = "PanSdkModule"

    @ReactMethod
    fun show() {
        PanSdk.show(currentActivity!!)
    }
}
