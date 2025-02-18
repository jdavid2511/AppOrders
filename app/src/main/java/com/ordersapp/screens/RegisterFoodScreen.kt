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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ordersapp.app.PostOfficeAppRouter
import com.ordersapp.app.Screen
import com.ordersapp.components.DoubleTextComponents
import com.ordersapp.components.HeadingTextComponents
import com.ordersapp.components.boxChairComponent
import com.ordersapp.components.buttonaddComponent
import com.ordersapp.presentation.ProductState
import com.ordersapp.presentation.TableState

@Composable
fun  RegisterfoodScreen(productState: ProductState, tableState: TableState) {

    BackHandler (enabled = true) {
        PostOfficeAppRouter.onBack()
    }

    Surface (
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 50.dp, start = 28.dp, end = 28.dp)
    ) {
        Column {
            HeadingTextComponents(value = "Agregar Mesa", fontWeigth = FontWeight.Bold)

            buttonaddComponent(178, 16, onClick = {PostOfficeAppRouter.navigateTo(Screen.CategoriesScreen)})

            Row (modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)) {
                DoubleTextComponents("Mesas", "Ver Más")
            }
            Column (){
                LazyColumn {
                    items(tableState.tables){ table ->
                        boxChairComponent("Mesa #" + table.name, table.total.toString())
                    }
                }
            }
        }
    }
}