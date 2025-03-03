package com.ordersapp.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ordersapp.components.DoubleTextComponents
import com.ordersapp.components.NormalTextComponents
import com.ordersapp.components.TopAccessComponent
import com.ordersapp.components.TotalAccountComponent
import com.ordersapp.components.buttonaddComponent
import com.ordersapp.components.productTableComponent
import com.ordersapp.data.product.Product
import com.ordersapp.navigation.Routes
import com.ordersapp.viewModel.ProductViewModel
import com.ordersapp.viewModel.TableProductsCrossRefViewModel
import com.ordersapp.viewModel.TableViewModel

@Composable
fun TableOrderScreen(
    navHostController: NavHostController,
    tableViewModel: TableViewModel,
    productViewModel: ProductViewModel,
    tableInt: Int,
    tableProductsCrossRefViewModel: TableProductsCrossRefViewModel
) {

    BackHandler (enabled = true) {
        navHostController.navigateUp()
    }

    Surface (
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 50.dp)
    ) {
        var total by remember { mutableStateOf(0L) }
        Column (
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column {
                TopAccessComponent(table = "Mesa #$tableInt", navHostController = navHostController)
                NormalTextComponents(value = "Agregar", 10)
                buttonaddComponent(70, 5, onClick = {navHostController.navigate(Routes.CategoriesScreen.createRoute(tableInt))})
                Spacer(modifier = Modifier.padding(10.dp))
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)) {
                    DoubleTextComponents("Pedido", "Borrar todo", tableViewModel = tableViewModel)
                }
                Spacer(modifier = Modifier.padding(5.dp))

                Column (
                    modifier = Modifier
                        .height(400.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    val productsCrossRef by tableProductsCrossRefViewModel.getAllItems(tableInt.toString()).collectAsState(initial = emptyList())
                    LazyColumn {
                        items(productsCrossRef) { tableProduct ->
                            val currentTotal by tableViewModel.getTotalByTableId(tableProduct.tableId).collectAsState(initial = 0L)
                            total = currentTotal
                            val product by productViewModel.getProductById(tableProduct.productId).collectAsState( Product(0,"",0,0))
                            productTableComponent(
                                tableViewModel,
                                tableProduct.tableId,
                                tableProduct.productId,
                                product.name,
                                product.price.toString(),
                                tableProduct.quantity,
                                total
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .navigationBarsPadding(),
                contentAlignment = Alignment.BottomCenter
            ) {
                TotalAccountComponent("Total", total = total.toString())
            }
        }
    }
}