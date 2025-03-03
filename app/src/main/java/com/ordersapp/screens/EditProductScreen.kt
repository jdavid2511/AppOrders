package com.ordersapp.screens

import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ordersapp.components.EditTextComponents
import com.ordersapp.components.buttonSaveComponent
import com.ordersapp.data.product.Product
import com.ordersapp.presentation.ProductState
import com.ordersapp.viewModel.ProductViewModel
import java.io.InputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductScreen(
    navHostController: NavHostController,
    productId: Int,
    productViewModel: ProductViewModel,
    productState: ProductState
) {
    val context = LocalContext.current

    val pickMedia =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                val inputStream: InputStream? = uri.let {
                    context.contentResolver.openInputStream(it)
                }
            }
        }

    BackHandler (enabled = true) {
        navHostController.navigateUp()
    }

    val product by productViewModel.getProductById(productId)
        .collectAsState(initial = Product(id = 0, name = "", price = 0, categoryId = 0))

    productState.id.value = product.id
    productState.name.value = product.name
    productState.price.value = product.price
    productState.categoryId.value = product.categoryId

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Añadir Producto", style = MaterialTheme.typography.headlineSmall) },
                navigationIcon = {
                    //TODO
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(30.dp))
            EditTextComponents(
                labelValue = "Nombre",
                imageVector = Icons.Default.Edit,
                keyboardType = KeyboardType.Text,
                value = productState.name.value,
                onValueChange = { productState.name.value = it })
            Spacer(modifier = Modifier.height(30.dp))
            EditTextComponents(
                labelValue = "Precio",
                imageVector = Icons.Default.CurrencyExchange,
                keyboardType = KeyboardType.Number,
                value = productState.price.value.toString(),
                onValueChange = { productState.price.value = it.toLong() })
            Spacer(modifier = Modifier.height(30.dp))
            Spacer(modifier = Modifier.height(16.dp))

            val newProduct = Product(
                id = productState.id.value,
                name = productState.name.value,
                price = productState.price.value,
                categoryId = productState.categoryId.value
            )

            buttonSaveComponent(onClick = {
                productViewModel.updateProduct(newProduct)
                navHostController.navigateUp()
            }, value = "Guardar")
        }
    }


}