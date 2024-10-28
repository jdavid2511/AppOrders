package com.ordersapp.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ordersapp.components.NormalTextComponents

@Composable
fun SignUpScreen () {
    Surface (
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        NormalTextComponents(value = "")
    }
}

@Preview
@Composable
fun DefaultPreviewOfSingUpScreen(){
    SignUpScreen()
}