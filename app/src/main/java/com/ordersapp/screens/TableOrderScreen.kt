package com.ordersapp.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ordersapp.components.DoubleTextComponents
import com.ordersapp.components.NormalTextComponents
import com.ordersapp.components.TopAccessComponent
import com.ordersapp.components.TotalAccountComponent
import com.ordersapp.components.buttonaddComponent
import com.ordersapp.components.productTableComponent

@Composable
fun TableOrderScreen () {

    Surface (
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 50.dp)
    ) {
        Column {
            TopAccessComponent(table = "Mesa #1")
            NormalTextComponents(value = "Agregar", 10)
            buttonaddComponent(70, 5)
            Spacer(modifier = Modifier.padding(10.dp))
            Row (modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)) {
                DoubleTextComponents("Pedido", "Borrar todo")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Column (
                modifier = Modifier
                    .height(400.dp)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                productTableComponent("Papas Fritas", "$15.000", "2")
                productTableComponent("Picada", "$5.000", "2")
                productTableComponent("Salchipapa", "$12.000", "2")
                productTableComponent("desgranado", "$10.000", "2")
            }
        }
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(8.dp)
                    .navigationBarsPadding(),
            contentAlignment = Alignment.BottomCenter
        ) {
            TotalAccountComponent("Total", "$45.000")
        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfTableOrderScreen(){
    TableOrderScreen()
}