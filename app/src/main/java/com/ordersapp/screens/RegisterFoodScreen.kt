package com.ordersapp.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ordersapp.components.DoubleTextComponents
import com.ordersapp.components.HeadingTextComponents
import com.ordersapp.components.boxChairComponent
import com.ordersapp.components.buttonaddComponent
import com.ordersapp.navigation.Routes
import com.ordersapp.viewModel.TableViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun  RegisterfoodScreen(
    tableViewModel: TableViewModel,
    navHostController: NavHostController,
) {

    BackHandler (enabled = true) {
        navHostController.navigateUp()
    }
    var showNewScreen by remember { mutableStateOf(false) }
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
                showNewScreen = true
            })
            if (showNewScreen) {
                ShowTableScreen(tableViewModel = tableViewModel, navHostController) // Se muestra cuando se hace clic en el botón
            }

            Row (modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)) {
                DoubleTextComponents("Mesas", "Ver Más", tableViewModel = tableViewModel)
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

@Composable
fun TableDialog(
    onDismiss: () -> Unit,
    tableViewModel: TableViewModel,
    navHostController: NavHostController
) {
    var tableNumber by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var checkTable by remember { mutableStateOf(false) } // Estado para activar la verificación

    // Ejecutar la verificación cuando `checkTable` cambie a true
    LaunchedEffect(checkTable) {
        if (checkTable) {
            val exists = withContext(Dispatchers.IO) { tableViewModel.checkTable(tableNumber) }
            if (!exists) {
                errorMessage = "Esta mesa ya está registrada"
            } else {
                navHostController.navigate(Routes.CategoriesScreen.createRoute(tableNumber.toInt()))
            }
            checkTable = false // Resetear el estado después de la verificación
        }
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Ingrese el número de la mesa") },
        text = {
            Column {
                OutlinedTextField(
                    value = tableNumber,
                    onValueChange = { tableNumber = it },
                    label = { Text("Número de mesa") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = errorMessage.isNotEmpty()
                )
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                if (tableNumber.isBlank()) {
                    errorMessage = "Ingrese un número válido"
                    return@Button
                }
                checkTable = true // Activar la verificación en `LaunchedEffect`
            }) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancelar")
            }
        }
    )
}


@Composable
fun ShowTableScreen(tableViewModel: TableViewModel, navHostController: NavHostController) {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { showDialog = true }) {
            Text("Agregar Mesa")
        }

        if (showDialog) {
            TableDialog(
                onDismiss = { showDialog = false },
                tableViewModel = tableViewModel,
                navHostController = navHostController
            )
        }
    }
}