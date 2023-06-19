package com.example.forunisoldev.domain.usecase

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class RequestCameraPermission(
    private val context: Context
) {
    fun execute(): CameraPermissionResult {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
        return CameraPermissionResult.Granted
        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity,
                Manifest.permission.CAMERA
            )
        ) {
            return CameraPermissionResult.Denied
        }

        ActivityCompat.requestPermissions(
            context,
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE
        )

        // Результат запроса разрешения будет обработан в onRequestPermissionsResult()

        return CameraPermissionResult.Pending
    }

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 1
    }
}

sealed class CameraPermissionResult {
    object Granted : CameraPermissionResult()
    object Denied : CameraPermissionResult()
    object Pending : CameraPermissionResult()
}