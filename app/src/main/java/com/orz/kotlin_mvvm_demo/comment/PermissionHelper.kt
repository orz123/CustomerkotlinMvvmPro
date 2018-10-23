package com.orz.kotlin_mvvm_demo.comment


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.Log

import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions

import io.reactivex.Observable

/**
 * 权限帮助类
 */
object PermissionHelper {

    val isOverMarshmallow: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    fun requestPermissions(activity: Activity, onComplete: (() -> Unit)? = null) {
        requestPermissions(activity)
                .subscribe({ Log.i("permission granted:", "${it.name}, ${it.granted}" ) },
                        { Log.i("permission err:"," ${it.message} ") },
                        { onComplete?.invoke() })
    }

    fun requestPermissions(activity: Activity): Observable<Permission> {
        val rxPermissions = RxPermissions(activity)
        return rxPermissions
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE)
    }

    fun checkStoragePermission(context: Context): Boolean {
        return checkPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    fun checkCameraPermission(context: Context): Boolean {
        return checkPermission(context, Manifest.permission.CAMERA)
    }

    fun checkLocationPermission(context: Context): Boolean {
        return checkPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
    }

    fun checkPermission(context: Context, permission: String): Boolean {
        return if (!isOverMarshmallow) {
            true
        } else ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }
}
