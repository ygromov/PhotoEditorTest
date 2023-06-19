package com.example.forunisoldev

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.compose.rememberImagePainter
import com.example.forunisoldev.domain.usecase.CameraPermissionResult
import com.example.forunisoldev.domain.usecase.RequestCameraPermission
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

private const val TAG = "MainActivity"


class MainActivity : ComponentActivity() {

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)
    private lateinit var photoUri: Uri
    private var shouldShowPhoto: MutableState<Boolean> = mutableStateOf(false)

    private var rp = RequestCameraPermission

    /* это функция регистрации для получения результата действия пользователя.
    В данном случае, мы регистрируем ActivityResultContracts.RequestPermission(),
    который будет использоваться для запроса разрешений.
    * */
    private val requestPermissionLauncher =
        registerForActivityResult(        //выдача разрешений на открытие
            ActivityResultContracts.RequestPermission()
        ) {                     //сюда приходит true или false
            if (it) {
                Log.d(TAG, ": permission granted")
            } else {
                Log.d(TAG, ": permission denied")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if(rp.equals(CameraPermissionResult.Granted)){      //для проверки, потом перенести во вьюмодель
            //if (shouldShowCamera.value) {           //если true, то создаем экземпляр CameraView
                CameraView(
                    outputDirectory = outputDirectory,  //каталог для сохранения снимков
                    executor = cameraExecutor,              //исполнитель для обработки фотографий(новые задачи не запустятся, а старые не тронет)
                    onImageCaptured = ::handleImageCapture,     //если изображение есть, то скрываем камеру и выводим изображение
                    onError = { Log.e("kilo", "View error:", it) }
                )
            }

            if (shouldShowPhoto.value) {            //если true, то создаем выводим на экран захваченный кадр
                Image(
                    painter = rememberImagePainter(photoUri),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                Button(onClick = {                  //кнопка закрытия фотографии и вызова интерфейса камеры
                    shouldShowPhoto.value = false
                    shouldShowCamera.value = true
                }) {
                    Text(text = "close")
                }
            }
        }
        rp

        //requestCameraPermission()

        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }


//    private fun requestCameraPermission() {
//        when {
//            ContextCompat.checkSelfPermission(      //было ли разрешение для камеры
//                this,
//                Manifest.permission.CAMERA
//            ) == PackageManager.PERMISSION_GRANTED -> {
//                Log.i("kilo", "Permission previously granted")
//                shouldShowCamera.value = true
//            }
//
//            ActivityCompat.shouldShowRequestPermissionRationale(    //если юзер отклонил, то уточнить зачем нужно разрешение
//                this,
//                Manifest.permission.CAMERA
//            ) -> Log.i("kilo", "Show camera permissions dialog")
//
//            else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA) //если разрешения не было и диалогового не было, то спросить разрешение
//        }
//    }

    private fun handleImageCapture(uri: Uri) {
        Log.i("kilo", "Image captured: $uri")
        shouldShowCamera.value =
            false              //если изображение захвачено, ставим false для скрытия интерфейс камеры

        photoUri = uri
        shouldShowPhoto.value = true                //ставим true, чтоб показать изображение

    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(
                it,
                resources.getString(R.string.app_name)
            ).apply { mkdirs() } //возвращаем, если mediaDir существует или не равен нулю
        }

        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir    //если mediaDir не существует или равен null, то запись filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}