package com.ordersapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ordersapp.data.product.Product

data class ProductState (
    val products: List<Product> = emptyList(),
    val id : MutableState<Int> = mutableStateOf(0),
    val name : MutableState<String> = mutableStateOf(""),
    val price : MutableState<String> = mutableStateOf(""),
    var categoryId : MutableState<Int> = mutableStateOf(0),
)