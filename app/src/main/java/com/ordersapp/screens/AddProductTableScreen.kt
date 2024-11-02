package com.ordersapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ordersapp.components.NormalTextComponents

@Composable
fun AddProductTableScreen(){
    Surface (
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 50.dp, start = 28.dp, end = 28.dp),
    ) {
        Column {
            NormalTextComponents(value = "Agregar Productos", heightInt = 10)

        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfAddProductTableScreen(){
    AddProductTableScreen()
}