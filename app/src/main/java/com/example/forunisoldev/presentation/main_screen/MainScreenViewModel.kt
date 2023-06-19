package com.example.forunisoldev.presentation.main_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import coil.compose.rememberImagePainter
import com.example.forunisoldev.CameraView
import com.example.forunisoldev.domain.models.ShouldShowCamera

class MainScreenViewModel: ViewModel() {
    val state = mutableStateOf(MainState())




//    if (shouldShowCamera.value) {           //если true, то создаем экземпляр CameraView
//        CameraView(
//            outputDirectory = outputDirectory,  //каталог для сохранения снимков
//            executor = cameraExecutor,              //исполнитель для обработки фотографий(новые задачи не запустятся, а старые не тронет)
//            onImageCaptured = ::handleImageCapture,     //если изображение есть, то скрываем камеру и выводим изображение
//            onError = { Log.e("kilo", "View error:", it) }
//        )
//    }
//
//    if (shouldShowPhoto.value) {            //если true, то создаем выводим на экран захваченный кадр
//        Image(
//            painter = rememberImagePainter(photoUri),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize()
//        )
//        Button(onClick = {                  //кнопка закрытия фотографии и вызова интерфейса камеры
//            shouldShowPhoto.value = false
//            shouldShowCamera.value = true
//        }) {
//            Text(text = "close")
//        }
//    }
}