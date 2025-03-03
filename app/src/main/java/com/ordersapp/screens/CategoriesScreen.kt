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
import androidx.navigation.NavHostController
import com.ordersapp.R
import com.ordersapp.components.ButtonAddProduct
import com.ordersapp.components.HeadingTextComponents
import com.ordersapp.navigation.Routes
import com.ordersapp.presentation.ProductState
import com.ordersapp.viewModel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    productViewModel: ProductViewModel,
    productState: ProductState,
    navHostController: NavHostController,
    tableId: Int,
) {
    println("+++++++++++++$tableId")
    BackHandler (enabled = true) {
        navHostController.navigateUp()
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Categorias", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigateUp() }) {
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

                    val categories = listOf(
                        Pair("Comida Rápida", R.drawable.burger),
                        Pair("Bebidas Frías", R.drawable.drink),
                        Pair("Bebidas Calientes", R.drawable.drink),
                        Pair("Almuerzos", R.drawable.fries),
                        Pair("Almuerzos", R.drawable.fries),
                        Pair("Almuerzos", R.drawable.fries)
                    )

                    categories.forEachIndexed { index, (name, image) ->
                        item {
                            ButtonAddProduct(
                                categoryName = name,
                                image = image,
                                onClick = { navHostController.navigate(Routes.ListOfProducts.createRoute(categoryId = index + 1, tableId = tableId)) }
                            )
                        }
                    }
                }
            }
        }
    }
}