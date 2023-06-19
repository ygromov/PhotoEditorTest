package com.example.forunisoldev.presentation.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen() {
    Column() {
        Button(onClick = {  }) {
            Text(text = "Camera")
        }
        Button(
            modifier = Modifier
                .padding(top = 20.dp),
            onClick = { /*TODO*/ }) {
            Text(text = "Photo Gallery")
        }
    }
}