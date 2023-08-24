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
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longhrk.app.ui.components.model.DialogType
import com.longhrk.app.ui.extensions.shareOtherApp
import com.longhrk.app.ui.extensions.writeBitmapToUri
import com.longhrk.app.ui.viewmodel.drag.model.DrawUIState
import com.longhrk.app.ui.viewmodel.drag.model.TypeExpanded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DrawSignatureViewModel @Inject constructor() : ViewModel() {

    private var _currentTypeExpanded = MutableStateFlow(TypeExpanded.NONE)
    val currentTypeExpanded = _currentTypeExpanded.asStateFlow()

    private var _currentStateUI = MutableStateFlow(DrawUIState())
    val currentStateUI = _currentStateUI.asStateFlow()

    private var _currentDrawColor = MutableStateFlow("#FF0000")
    val currentDrawColor = _currentDrawColor.asStateFlow()

    private var _currentStrokeWidth = MutableStateFlow(10f)
    val currentStrokeWidth = _currentStrokeWidth.asStateFlow()

    private var _dialogCurrent = MutableStateFlow<DialogType?>(null)
    val dialogCurrent = _dialogCurrent.asStateFlow()

    private var _uriFromPhoto = MutableStateFlow<Uri>(Uri.EMPTY)
    val uriFromPhoto = _uriFromPhoto.asStateFlow()

    fun updateDialog(type: DialogType?) {
        _dialogCurrent.value = type
    }

    fun updateCurrentColor(colorString: String) {
        _currentDrawColor.value = colorString
    }

    fun updateCurrentStateUI(state: DrawUIState) {
        _currentStateUI.value = state
    }

    fun updateCurrentStrokeWidth(width: Float) {
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
                context.writeBitmapToUri(bitmap, imageUri)
            }
            withContext(Dispatchers.Main) {
                imageUri?.let { _uriFromPhoto.value = it }
                updateDialog(DialogType.ONE_BUTTON)
            }
        }
    }

    fun shareUriOutApplication(context: Context, uri: Uri) {
        viewModelScope.launch {
            context.shareOtherApp(uri)
        }
    }
}