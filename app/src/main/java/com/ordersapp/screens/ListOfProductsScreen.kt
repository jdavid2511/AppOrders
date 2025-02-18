package com.ordersapp.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ordersapp.app.PostOfficeAppRouter
import com.ordersapp.app.Screen
import com.ordersapp.presentation.ProductState
import com.ordersapp.viewModel.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfProducts(
    viewModel: ProductViewModel,
    state: ProductState,
    categoryId: Int
) {
    BackHandler (enabled = true) {
        PostOfficeAppRouter.onBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bebidas", style = MaterialTheme.typography.headlineSmall)},
                navigationIcon = {
                    IconButton(onClick = { PostOfficeAppRouter.onBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                },

            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    PostOfficeAppRouter.navigateTo(Screen.AddProductScreen(categoryId))
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
    state: ProductState
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .combinedClickable(
                onClick = { /* Acción para clic corto */ },
                onLongClick = { /* Acción para clic largo */ }
            )
            .clip(RoundedCornerShape(12.dp)),
        //colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Contact image",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onPrimaryContainer)
                    .padding(16.dp),
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(12.dp))
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
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = {
                    state.id.value = id
                    state.name.value = name
                    state.price.value = price
                    state.categoryId.value = categoryId
                    viewModel.deleteProduct()
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

