package com.echolabs.custompassword.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.content.ContextCompat

object VibrateUtils {


    fun vibrate(context: Context) {

        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.VIBRATE
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?

            if (Build.VERSION.SDK_INT >= 26) {
                vibrator?.vibrate(VibrationEffect.createOneShot(150, 10))
            } else {
                vibrator?.vibrate(longArrayOf(0, 250, 25, 150), -1)
            }
        }
    }
}