package com.ordersapp.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ordersapp.screens.AddProductTableScreen
import com.ordersapp.presentation.ProductState
import com.ordersapp.screens.RegisterfoodScreen
import com.ordersapp.screens.SignInScreen
import com.ordersapp.screens.TableOrderScreen
import com.ordersapp.viewModel.ProductViewModel

@Composable
fun PostOfficeApp(viewModel: ProductViewModel, productState : ProductState) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = PostOfficeAppRouter.currentScreen) { currentState ->
            when (currentState.value) {
                is Screen.RegisterFoodScreen -> {
                    RegisterfoodScreen(productState = productState)
                }
                is Screen.SignInScreen -> {
                    SignInScreen()
                }
                is Screen.TableOrderScreen -> {
                    TableOrderScreen()
                }
                is Screen.AddProductTableScreen -> {
                    AddProductTableScreen(viewModel = viewModel, productState = productState)
                }
            }

        }
    }
}