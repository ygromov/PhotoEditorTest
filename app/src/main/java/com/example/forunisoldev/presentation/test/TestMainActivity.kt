package com.example.forunisoldev.presentation.test

// Импорт необходимых пакетов
import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
//import androidx.compose.material.Button
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

// Создание ViewModel с использованием ViewModelProvider
//class CameraViewModel : ViewModel() {
//    // Состояние для отображения фотографии
//    val capturedImage = mutableStateOf<Uri?>(null)
//
//    // Состояние для доступности кнопки фотографирования
//    val isCaptureButtonEnabled = mutableStateOf(true)
//
//    // Инициализация CameraX и ImageCapture
//    private lateinit var imageCapture: ImageCapture
//
//    init {
//        initializeCamera()
//    }


//    private fun initializeCamera() {
//        val cameraProviderFuture = ProcessCameraProvider.getInstance(LocalContext.current)
//        cameraProviderFuture.addListener(Runnable {
//            val cameraProvider = cameraProviderFuture.get()
//
//            // Выбор задней камеры
//            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//
//            // Настройка ImageCapture сборщика
//            imageCapture = ImageCapture.Builder().build()
//
//            try {
//                // Привязка ImageCapture к жизненному циклу
//                cameraProvider.bindToLifecycle((LocalContext.current as ComponentActivity), cameraSelector, imageCapture)
//            } catch (exception: Exception) {
//                // Обработка ошибки
//            }
//        }, ContextCompat.getMainExecutor(LocalContext.current))
//    }
//
//    // Функция для фотографирования
//    fun capturePhoto() {
//        val imageCapture = imageCapture ?: return
//
//        // Определение файла для сохранения фотографии
//        val photoFile = createFile()
//
//        // Настройка параметров для сохранения фотографии
//        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
//
//        // Сделать снимок
//        imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(LocalContext.current),
//            object : ImageCapture.OnImageSavedCallback {
//                override fun onError(exception: ImageCaptureException) {
//                    // Обработка ошибки
//                }
//
//                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
//                    // Обновить состояние с фотографией
//                    capturedImage.value = Uri.fromFile(photoFile)
//                }
//            }
//        )
//    }
//
//    // Функция для создания файла для сохранения фотографии
//    @Composable
//    private fun createFile(): File {
//        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
//        val storageDir = LocalContext.current.getExternalFilesDir(null)
//        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
//    }
//}
//
//// Главная функция приложения
//class MainActivity : ComponentActivity() {
//    private val permissions = arrayOf(Manifest.permission.CAMERA)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Проверка разрешений на камеру
//        if (allPermissionsGranted()) {
//            startCamera()
//        } else {
//            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_PERMISSIONS)
//        }
//    }
//
//    private fun allPermissionsGranted() = permissions.all {
//        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
//    }
//
//    private fun startCamera() {
//        setContent {
//            Surface(color = MaterialTheme.colors.background) {
//                CameraScreen()
//            }
//        }
//    }
//
//    companion object {
//        private const val REQUEST_CODE_PERMISSIONS = 10
//    }
//}
//
//@Composable
//fun CameraScreen(viewModel: CameraViewModel = viewModel()) {
//    Column {
//        // Отображение фотографии
//        val imageUri = viewModel.capturedImage.value
//        if (imageUri != null) {
//            Image(
//                bitmap = loadImage(imageUri),
//                contentDescription = "Captured image",
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth()
//            )
//        }
//
//        // Кнопка фотографирования
//        Button(
//            onClick = {
//                viewModel.capturePhoto()
//            },
//            enabled = viewModel.isCaptureButtonEnabled.value,
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth()
//        ) {
//            Text(text = "Capture Photo")
//        }
//    }
//}
//
////@Composable
////fun loadImage(uri: Uri): ImageBitmap {
////    // Загрузка изображения с помощью Glide или любой другой библиотеки
////    return // Load image bitmap here
////}
//
//@Preview
//@Composable
//fun PreviewCameraScreen() {
//    Surface(color = MaterialTheme.colorScheme.background) {
//        CameraScreen()
//    }
//}
//
//// ViewModelProvider.Factory для создания ViewModel
//class CameraViewModelFactory : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(CameraViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return CameraViewModel() as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
//
//// Функция onRequestPermissionsResult для обработки разрешений
//override fun onRequestPermissionsResult(
//    requestCode: Int,
//    permissions: Array<String>,
//    grantResults: IntArray
//) {
//    if (requestCode == REQUEST_CODE_PERMISSIONS) {
//        if (allPermissionsGranted()) {
//            startCamera()
//        } else {
//            // Разрешения не были предоставлены
//            finish()
//        }
//    }
//}