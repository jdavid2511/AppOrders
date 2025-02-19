package com.ordersapp.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ordersapp.R
import com.ordersapp.app.PostOfficeAppRouter
import com.ordersapp.app.Screen
import com.ordersapp.components.ButtonAddProduct
import com.ordersapp.components.HeadingTextComponents
import com.ordersapp.presentation.ProductState
import com.ordersapp.viewModel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(productViewModel: ProductViewModel, productState: ProductState) {

    BackHandler (enabled = true) {
        PostOfficeAppRouter.onBack()
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Categorias", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { PostOfficeAppRouter.onBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                },

                )
        },
    ) {

        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 50.dp, start = 28.dp, end = 28.dp),
        ) {

            Column {

                HeadingTextComponents(value = "Categorias", fontWeigth = FontWeight.Bold)

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // Dos columnas
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    item {
                        ButtonAddProduct(categoryName = "comida Rapida", image = R.drawable.burger, onClick = { PostOfficeAppRouter.navigateTo(
                            Screen.ListOfProducts(categoryId = 1))})
                    }
                    item {
                        ButtonAddProduct(categoryName = "Bebidas Frias", image = R.drawable.drink, onClick = { PostOfficeAppRouter.navigateTo(
                            Screen.ListOfProducts(categoryId = 2))})
                    }
                    item {
                        ButtonAddProduct(categoryName = "Bebidas Calientes", image = R.drawable.drink, onClick = { PostOfficeAppRouter.navigateTo(
                            Screen.ListOfProducts(categoryId = 3))})
                    }
                    item {
                        ButtonAddProduct(categoryName = "Almuerzos", image = R.drawable.fries, onClick = { PostOfficeAppRouter.navigateTo(
                            Screen.ListOfProducts(categoryId = 4))})
                    }
                    item {
                        ButtonAddProduct(categoryName = "Almuerzos", image = R.drawable.fries, onClick = { PostOfficeAppRouter.navigateTo(
                            Screen.ListOfProducts(categoryId = 5))})
                    }
                    item {
                        ButtonAddProduct(categoryName = "Almuerzos", image = R.drawable.fries, onClick = { PostOfficeAppRouter.navigateTo(
                            Screen.ListOfProducts(categoryId = 6))})
                    }
                }
            }
        }
    }
}