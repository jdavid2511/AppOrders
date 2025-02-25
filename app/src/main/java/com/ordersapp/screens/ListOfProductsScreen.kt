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
import com.ordersapp.components.DialogWithImage
import com.ordersapp.navigation.Routes
import com.ordersapp.presentation.ProductState
import com.ordersapp.viewModel.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfProducts(
    navHostController: NavHostController,
    viewModel: ProductViewModel,
    state: ProductState,
    categoryId: Int
) {
    BackHandler (enabled = true) {
        navHostController.navigateUp()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bebidas", style = MaterialTheme.typography.headlineSmall)},
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
        val products by viewModel.getProductsByCategory(categoryId)
            .collectAsState(initial = emptyList())

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
                        viewModel = viewModel,
                        state = state,
                        name = product.name,
                        price = product.price,
                        id = product.id,
                        categoryId = categoryId,
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
    price: String,
    id: Int,
    categoryId: Int,
    viewModel: ProductViewModel,
    state: ProductState,
    navHostController: NavHostController
) {
    val context = LocalContext.current
    val openDialogWithImage = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .combinedClickable(
                onClick = {  },
                onLongClick = { openDialogWithImage.value = !openDialogWithImage.value }
            )
            .clip(RoundedCornerShape(12.dp)),
        //colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        when {
            openDialogWithImage.value -> {
                DialogWithImage(
                    onDismissRequest = { openDialogWithImage.value = false },
                    state = state,
                    productViewModel = viewModel,
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
                    text = price,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}



