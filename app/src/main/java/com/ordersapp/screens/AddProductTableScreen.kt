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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ordersapp.R
import com.ordersapp.components.ButtonAddProduct
import com.ordersapp.components.HeadingTextComponents
import com.ordersapp.components.NormalTextComponents

@Composable
fun AddProductTableScreen(){
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

@Preview
@Composable
fun DefaultPreviewOfAddProductTableScreen(){
    AddProductTableScreen()
}