package com.ordersapp.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ordersapp.R
import com.ordersapp.components.DialogWithImage
import com.ordersapp.data.table.Table
import com.ordersapp.navigation.Routes
import com.ordersapp.presentation.ProductState
import com.ordersapp.viewModel.ProductViewModel
import com.ordersapp.viewModel.TableViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfProducts(
    navHostController: NavHostController,
    productViewModel: ProductViewModel,
    tableViewModel: TableViewModel,
    productState: ProductState,
    categoryId: Int,
    tableId: Int
) {
    BackHandler (enabled = true) {
        navHostController.navigateUp()
    }
    Scaffold(
        topBar = {
            val categories = listOf(
                1 to "Comida Rápida",
                2 to "Bebidas Frías",
                3 to "Bebidas Calientes",
                4 to "Almuerzos"
            )
            // Obtener el nombre de la categoría seleccionada
            val categoryName = categories.firstOrNull { it.first == categoryId }?.second ?: "Desconocido"
            TopAppBar(
                title = { Text(categoryName, style = MaterialTheme.typography.headlineSmall)},
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Routes.AddProductScreen.createRoute(categoryId = categoryId))
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {
        val products by productViewModel.getProductsByCategory(categoryId)
            .collectAsState(initial = emptyList())

        println(products)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(products) { product ->
                    contactCard(
                        tableViewModel = tableViewModel,
                        productViewModel = productViewModel,
                        state = productState,
                        name = product.name,
                        price = product.price,
                        id = product.id,
                        categoryId = categoryId,
                        tableId = tableId,
                        navHostController = navHostController
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun contactCard(
    name: String,
    price: Long,
    id: Int,
    categoryId: Int,
    productViewModel: ProductViewModel,
    tableViewModel: TableViewModel,
    state: ProductState,
    tableId: Int,
    navHostController: NavHostController
) {
    val openDialogWithImage = remember { mutableStateOf(false) }

    val currentTotal by tableViewModel.getTotalByTableId(tableId).collectAsState(initial = 0L)

    println(currentTotal)

    val table = Table (
        id = tableId,
        total = (currentTotal ?: 0L) + price
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .combinedClickable(
                onClick = {
                    tableViewModel.saveTableWithProducts(table, id)
                    navHostController.navigate(Routes.TableOrderScreen.createRoute(tableId))
                },
                onLongClick = { openDialogWithImage.value = !openDialogWithImage.value }
            )
            .clip(RoundedCornerShape(12.dp)),
    ) {
        when {
            openDialogWithImage.value -> {
                DialogWithImage(
                    onDismissRequest = { openDialogWithImage.value = false },
                    state = state,
                    productViewModel = productViewModel,
                    name = name,
                    price = price,
                    id = id,
                    categoryId = categoryId,
                    navHostController = navHostController
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(6.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = price.toString(),
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}



