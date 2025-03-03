package com.ordersapp.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ordersapp.components.DoubleTextComponents
import com.ordersapp.components.HeadingTextComponents
import com.ordersapp.components.TableDialog
import com.ordersapp.components.boxChairComponent
import com.ordersapp.components.buttonaddComponent
import com.ordersapp.viewModel.TableViewModel

@Composable
fun  RegisterfoodScreen(
    tableViewModel: TableViewModel,
    navHostController: NavHostController,
) {

    BackHandler (enabled = true) {
        navHostController.navigateUp()
    }
    var showDialog by remember { mutableStateOf(false) }
    Surface (
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 50.dp, start = 28.dp, end = 28.dp)
    ) {
        Column {
            HeadingTextComponents(value = "Agregar Mesa", fontWeigth = FontWeight.Bold)

            buttonaddComponent(178, 16, onClick = {
                showDialog = true
            })
            if (showDialog) {
                TableDialog(
                    onDismiss = { showDialog = false },
                    tableViewModel = tableViewModel,
                    navHostController = navHostController
                )
            }

            Row (modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)) {
                DoubleTextComponents(
                    "Mesas",
                    "Ver Más",
                    1,
                    tableViewModel = tableViewModel,
                    {}
                )
            }
            Column {
                LazyColumn {
                    items(tableViewModel.state.value.tables) { table ->
                        println(table)
                        boxChairComponent(
                            chair = "Mesa #",
                            tableId = table.id,
                            check = table.total.toString(),
                            navHostController = navHostController,
                        )
                    }
                }
            }
        }
    }
}