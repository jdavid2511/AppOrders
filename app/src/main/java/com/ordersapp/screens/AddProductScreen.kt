package com.ordersapp.screens

import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ordersapp.app.PostOfficeAppRouter
import com.ordersapp.app.Screen
import com.ordersapp.components.EditTextComponents
import com.ordersapp.components.buttonSaveComponent
import com.ordersapp.presentation.ProductState
import com.ordersapp.viewModel.ProductViewModel
import java.io.InputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(viewModel: ProductViewModel, productState: ProductState, categoryId: Int) {
    productState.categoryId.value = categoryId
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
        PostOfficeAppRouter.onBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add & Edit Contact", style = MaterialTheme.typography.headlineSmall) },
                navigationIcon = {
                    //TODO
                    IconButton(onClick = { PostOfficeAppRouter.navigateTo(Screen.ListOfProducts(categoryId = 1)) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(150.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Contact image",
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .padding(24.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )

                IconButton(
                    onClick = {
                        pickMedia.launch(
                            androidx.activity.result.PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    },
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            EditTextComponents(labelValue = "Nombre", imageVector = Icons.Default.Edit, keyboardType = KeyboardType.Text, value = productState.name.value, onValueChange = { productState.name.value = it })
            Spacer(modifier = Modifier.height(30.dp))
            EditTextComponents(labelValue = "Precio", imageVector = Icons.Default.CurrencyExchange, keyboardType = KeyboardType.Number, value = productState.price.value, onValueChange = { productState.price.value = it })
            Spacer(modifier = Modifier.height(30.dp))
            Spacer(modifier = Modifier.height(16.dp))


            buttonSaveComponent(onClick = { viewModel.saveProduct() }, value = "Guardar")
        }
    }


}