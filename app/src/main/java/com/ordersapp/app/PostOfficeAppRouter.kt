package com.ordersapp.app

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {
    object SignInScreen : Screen()
    object RegisterFoodScreen : Screen()
    object TableOrderScreen : Screen()
    object CategoriesScreen : Screen()
    data class AddProductScreen(val categoryId: Int) : Screen()
    data class ListOfProducts(val categoryId: Int) : Screen()
}

object PostOfficeAppRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.RegisterFoodScreen)
    private var previousScreen: Screen? = null

    fun navigateTo(destination: Screen) {
        previousScreen = currentScreen.value  // Guardamos la pantalla actual antes de cambiar
        currentScreen.value = destination
    }

    fun onBack() {
        previousScreen?.let { previous ->
            currentScreen.value = previous
            previousScreen = null
        }
    }
}