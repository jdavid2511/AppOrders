package com.ordersapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ordersapp.components.DoubleTextComponents
import com.ordersapp.components.HeadingTextComponents
import com.ordersapp.components.boxChairComponent
import com.ordersapp.components.buttonaddComponent

@Composable
fun  RegisterfoodScreen() {
    Surface (
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 50.dp, start = 28.dp, end = 28.dp)
    ) {
        Column {
            HeadingTextComponents(value = "Agregar Mesa")

            buttonaddComponent(178, 16)

            Row (modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)) {
                DoubleTextComponents("Mesas", "Ver Más")
            }
            Column (modifier = Modifier.verticalScroll(rememberScrollState())){
                boxChairComponent("Mesa #1", "$45.000")
                boxChairComponent("Mesa #2", "$45.000")
                boxChairComponent("Mesa #9", "$45.000")
                boxChairComponent("Mesa #3", "$45.000")
            }
        }

    }
}

@Preview
@Composable
fun DefaultPreviewOfRegisterFoodScreen(){
    RegisterfoodScreen()
}