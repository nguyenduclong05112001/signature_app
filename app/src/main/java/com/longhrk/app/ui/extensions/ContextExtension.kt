package com.longhrk.app.ui.extensions

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.Closeable
import java.io.FileNotFoundException
import java.io.IOException
import java.io.OutputStream

fun Context.getVersionName(): String {
    return this.packageManager.getPackageInfo(
        this.packageName,
        0
    ).versionName
}

suspend fun Context.shareOtherApp(uri: Uri) {
    val shareIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_STREAM,
            uri
        )
        type = "image/jpeg"
    }

    withContext(Dispatchers.Main) {
        this@shareOtherApp.startActivity(Intent.createChooser(shareIntent, null))
    }
}

@Throws(FileNotFoundException::class)
fun Context.writeBitmapToUri(bitmap: Bitmap, outputUri: Uri?): Uri? {
    var outputStream: OutputStream? = null
    try {
        outputStream = this.contentResolver.openOutputStream(outputUri!!)
        outputStream?.let { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
    } finally {
        closeSafe(outputStream)
    }
    return outputUri
}

private fun closeSafe(closeable: Closeable?) {
    try {
        closeable?.close()
    } catch (ignored: IOException) {
        Timber.tag("TAG_ERROR").e(ignored.toString())
    }
}