package com.example.modalcard

import android.app.Activity
import androidx.fragment.app.FragmentActivity

object PanSdk {
    fun show(activity: Activity) {
        if (activity is FragmentActivity) {
            HelloSdkBottomSheet().show(activity.supportFragmentManager, "HelloSdkSheet")
        }
    }
}
