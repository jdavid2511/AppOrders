package com.ordersapp.app

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {
    object SignUpScreen : Screen()
    object SignInScreen : Screen()
    object RegisterFoodScreen : Screen()
    object TableOrderScreen : Screen()
}



object PostOfficeAppRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.TableOrderScreen)

    fun navigateTo(destination : Screen) {
        currentScreen.value = destination
    }
}