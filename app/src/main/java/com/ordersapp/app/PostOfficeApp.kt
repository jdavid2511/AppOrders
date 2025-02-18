package com.ordersapp.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ordersapp.screens.CategoriesScreen
import com.ordersapp.presentation.ProductState
import com.ordersapp.presentation.TableState
import com.ordersapp.screens.AddProductScreen
import com.ordersapp.screens.ListOfProducts
import com.ordersapp.screens.RegisterfoodScreen
import com.ordersapp.screens.SignInScreen
import com.ordersapp.screens.TableOrderScreen
import com.ordersapp.viewModel.ProductViewModel

@Composable
fun PostOfficeApp(viewModel: ProductViewModel, productState : ProductState, tableState: TableState) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Crossfade(targetState = PostOfficeAppRouter.currentScreen) { currentState ->
            when (val screen = currentState.value) {
                is Screen.RegisterFoodScreen -> {
                    RegisterfoodScreen(productState = productState, tableState = tableState)
                }
                is Screen.SignInScreen -> {
                    SignInScreen()
                }
                is Screen.TableOrderScreen -> {
                    TableOrderScreen(productViewModel = viewModel, productState = productState)
                }
                is Screen.CategoriesScreen -> {
                    CategoriesScreen(productViewModel = viewModel, productState = productState)
                }
                is Screen.AddProductScreen -> {
                    AddProductScreen(viewModel = viewModel, productState = productState, categoryId = screen.categoryId)
                }
                is Screen.ListOfProducts -> {
                    ListOfProducts(viewModel = viewModel, state = productState, categoryId = screen.categoryId)
                }
            }

        }
    }
}