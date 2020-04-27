package com.depromeet.watni.util

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.depromeet.watni.MainApplication


/*
 * Created by yunji on 22/04/2020
 */
object PermissionUtil {

    fun getPermissionSettingIntent() = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", MainApplication.getContext().packageName, null)
    ).apply {
        addCategory(Intent.CATEGORY_DEFAULT)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
}