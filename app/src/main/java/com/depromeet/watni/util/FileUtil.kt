package com.depromeet.watni.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import com.depromeet.watni.R
import com.depromeet.watni.conference.view.ConferenceEditActivity
import java.io.File
import java.io.IOException


/*
 * Created by yunji on 23/04/2020
 */
object FileUtil {

    fun uriToBitmap(uri: Uri, context: Context): Bitmap? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> {
            val source = ImageDecoder.createSource(context.contentResolver, uri)
            ImageDecoder.decodeBitmap(source)
        }
        else -> {
            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }
    }

    private fun createAndReturnImageDir(context: Context): String {
        val parentPath = context.getExternalFilesDir(Environment.DIRECTORY_DCIM)
        val imageDir = File("$parentPath/Pictures", ResourceUtil.getString(R.string.app_name_eng))

        if (!imageDir.exists()) {
            imageDir.mkdir()
        }

        return imageDir.path
    }

    fun getTmpFile(context: Context): File {
        val newFile = File(createAndReturnImageDir(context), getUniqueFileName())
        try {
            if (!newFile.exists()) {
                newFile.createNewFile()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return newFile
    }

    fun getTmpFileUri(context: Context, file: File): Uri {
        return FileProvider.getUriForFile(context, "com.depromeet.watni.provider", file)
    }

    fun getUniqueFileName(ext: String = "jpg") =
        "${ResourceUtil.getString(R.string.app_name_eng)}_${System.currentTimeMillis()}.$ext"

    fun removeFile(uri: Uri?) {
        if (uri == null) {
            Log.w(ConferenceEditActivity.TAG, "Uri is null")
        }

        val tmpFile = File(uri!!.path!!)
        if (tmpFile.exists()) {
            tmpFile.delete()
        }
    }
}