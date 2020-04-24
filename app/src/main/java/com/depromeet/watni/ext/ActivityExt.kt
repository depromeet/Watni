package com.depromeet.watni.ext

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.depromeet.watni.*
import com.depromeet.watni.conference.view.ConferenceEditActivity
import com.depromeet.watni.group.GroupState
import com.depromeet.watni.model.source.ConferenceRepository
import com.depromeet.watni.model.source.GroupRepository
import com.depromeet.watni.model.source.SignRepository
import com.depromeet.watni.util.PermissionUtil

/*
 * Created by yunji on 20/04/2020
 */
fun AppCompatActivity.getViewModelFactory(): ViewModelFactory =
    ViewModelFactory(this, SignRepository, GroupRepository, ConferenceRepository)

fun AppCompatActivity.getViewModelFactory(groupState: GroupState): ViewModelFactory =
    ViewModelFactory(this, SignRepository, GroupRepository, ConferenceRepository, groupState)

fun AppCompatActivity.checkPermissions(permissions: Array<String>): Boolean {
    permissions.forEach {
        if (checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED) {
            return false
        }
    }
    return true
}

fun AppCompatActivity.requestPermissions(
    permission: Array<String> = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
) {
    if (!checkPermissions(permission)) {
        requestPermissions(permission, REQUEST_PERMISSION_COUNT)
    }
}

fun AppCompatActivity.goToSettingPage() {
    startActivity(PermissionUtil.getPermissionSettingIntent())
}

fun AppCompatActivity.dispatchTakePictureIntent() {
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
        putExtra("return-data", true)
    }.also {
        startActivityForResult(it, REQUEST_IMAGE_CAPTURE)
    }
}

fun AppCompatActivity.dispatchOpenGalleryIntent() {
    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also {
        startActivityForResult(it, PICK_FROM_ALBUM)
    }
}

fun AppCompatActivity.dispatchImageCropIntent(uri: Uri?) {
    if (uri == null) {
        Log.w(ConferenceEditActivity.TAG, "Uri is null")
    }

    Intent("com.android.camera.action.CROP").apply {
        setDataAndType(uri, "image/*")
        putExtra("aspectX", 327)
        putExtra("aspectY", 222)
        putExtra("outputX", 327)
        putExtra("outputY", 222)
        putExtra("scale", true)
        putExtra("return-data", true)
    }.also {
        startActivityForResult(it, CROP_IMAGE)
    }
}