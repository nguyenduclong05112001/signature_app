package com.longhrk.app.ui.viewmodel.drag

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longhrk.app.ui.viewmodel.drag.model.TypeExpanded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.Closeable
import java.io.FileNotFoundException
import java.io.IOException
import java.io.OutputStream
import javax.inject.Inject


@HiltViewModel
class DrawSignatureViewModel @Inject constructor() : ViewModel() {

    private var _currentTypeExpanded = MutableStateFlow(TypeExpanded.NONE)
    val currentTypeExpanded = _currentTypeExpanded.asStateFlow()

    private var _currentDrawColor = MutableStateFlow("#FF0000")
    val currentDrawColor = _currentDrawColor.asStateFlow()

    private var _currentStrokeWidth = MutableStateFlow(10f)
    val currentStrokeWidth = _currentStrokeWidth.asStateFlow()

    fun updateCurrentColor(colorString: String){
        _currentDrawColor.value = colorString
    }

    fun updateCurrentStrokeWidth(width: Float){
        _currentStrokeWidth.value = width
    }

    fun updateTypeExpanded(type: TypeExpanded) {
        if (_currentTypeExpanded.value == type) {
            _currentTypeExpanded.value = TypeExpanded.NONE
        } else {
            _currentTypeExpanded.value = type
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun saveInStorage(bitmap: Bitmap, context: Context) {
        viewModelScope.launch {
            val resolver: ContentResolver = context.contentResolver
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, System.currentTimeMillis())
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
            contentValues.put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                Environment.DIRECTORY_PICTURES + "/QRPAYByLongHRK"
            )

            val imageUri =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            withContext(Dispatchers.IO) {
                writeBitmapToUri(context, bitmap, imageUri)
            }
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Save photo completed", Toast.LENGTH_LONG).show()
                imageUri?.let { shareOtherApp(context, it) }
            }
        }
    }

    private fun shareOtherApp(context: Context, uri: Uri) {
        viewModelScope.launch {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_STREAM,
                    uri
                )
                type = "image/jpeg"
            }
            withContext(Dispatchers.Main) {
                context.startActivity(Intent.createChooser(shareIntent, null))
            }
        }
    }
}

@Throws(FileNotFoundException::class)
fun writeBitmapToUri(context: Context, bitmap: Bitmap, outputUri: Uri?): Uri? {
    var outputStream: OutputStream? = null
    try {
        outputStream = context.contentResolver.openOutputStream(outputUri!!)
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
        Log.e("TAG_ERROR", ignored.toString())
    }
}