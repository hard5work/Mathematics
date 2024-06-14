package com.xdroid.app.mathematics.utils.helpers

import android.util.Log
import com.xdroid.app.mathematics.BuildConfig

object DebugMode {
    fun e(tag: String, message: String, topic: String = "Failed") {
        if (BuildConfig.DEBUG) {
            Log.e("D $tag", "D $topic -> $message")
        }
    }
    fun e(message: String, topic: String = "Failed") {
        if (BuildConfig.DEBUG) {
            Log.e("D Compose", "D $topic -> $message")
        }
    }
}