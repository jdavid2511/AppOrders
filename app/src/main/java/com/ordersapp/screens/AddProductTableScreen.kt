package com.ordersapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ordersapp.components.EditTextComponents
import com.ordersapp.components.ExampleScreen
import com.ordersapp.components.NormalTextComponents
import com.ordersapp.components.buttonSaveComponent
import com.ordersapp.presentation.ProductState
import com.ordersapp.viewModel.ProductViewModel

@Composable
fun AddProductTableScreen(viewModel: ProductViewModel, productState: ProductState){
    Surface (
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 50.dp, start = 28.dp, end = 28.dp),
    ) {
        Column {
            NormalTextComponents(value = "Agregar Producto", heightInt = 10)
            Spacer(modifier = Modifier.height(30.dp))
            ExampleScreen(productState = productState, viewModel = viewModel)
            Spacer(modifier = Modifier.height(30.dp))
            EditTextComponents(labelValue = "Nombre", imageVector = Icons.Default.Edit, keyboardType = KeyboardType.Text, value = productState.name.value, onValueChange = { productState.name.value = it })
            Spacer(modifier = Modifier.height(30.dp))
            EditTextComponents(labelValue = "Precio", imageVector = Icons.Default.CurrencyExchange, keyboardType = KeyboardType.Number, value = productState.price.value, onValueChange = { productState.price.value = it })
            Spacer(modifier = Modifier.height(30.dp))

            buttonSaveComponent(onClick = { viewModel.saveProduct() }, value = "Guardar")
        }
    }
}