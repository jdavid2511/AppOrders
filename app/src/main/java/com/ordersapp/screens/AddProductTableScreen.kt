package com.ordersapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ordersapp.R
import com.ordersapp.components.EditTextComponents
import com.ordersapp.components.NormalTextComponents
import com.ordersapp.components.buttonSaveComponent

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
            NormalTextComponents(value = "Agregar Categoría", heightInt = 10)
            Spacer(modifier = Modifier.height(15.dp))
            EditTextComponents(labelValue = "Categoría", painterResource = painterResource(id = R.drawable.user))

            Spacer(modifier = Modifier.height(15.dp))

            buttonSaveComponent(value = "Guardar")
        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfAddProductTableScreen(){
    AddProductTableScreen()
}