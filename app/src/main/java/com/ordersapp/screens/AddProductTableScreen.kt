package com.ordersapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ordersapp.components.EditTextComponents
import com.ordersapp.components.ExampleScreen
import com.ordersapp.R
import com.ordersapp.components.ButtonAddProduct
import com.ordersapp.components.HeadingTextComponents
import com.ordersapp.components.NormalTextComponents
import com.ordersapp.components.buttonSaveComponent
import com.ordersapp.presentation.ProductState
import com.ordersapp.viewModel.ProductViewModel

@Composable
fun AddProductTableScreen(viewModel: ProductViewModel, productState: ProductState){
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 50.dp, start = 28.dp, end = 28.dp),
    ) {
        Column {
            HeadingTextComponents(value = "Agregar Productos")

            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Dos columnas
                modifier = Modifier.fillMaxSize()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    ButtonAddProduct(categoryName = "comida Rapida", image = R.drawable.burger)
                }
                item {
                    ButtonAddProduct(categoryName = "Bebidas Frias", image = R.drawable.drink)
                }
                item {
                    ButtonAddProduct(categoryName = "Bebidas Calientes", image = R.drawable.drink)
                }
                item {
                    ButtonAddProduct(categoryName = "Almuerzos", image = R.drawable.fries)
                }
                item {
                    ButtonAddProduct(categoryName = "Almuerzos", image = R.drawable.fries)
                }
                item {
                    ButtonAddProduct(categoryName = "Almuerzos", image = R.drawable.fries)
                }
            }
        }
    }
}