package com.ordersapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ordersapp.data.product.Product
import com.ordersapp.data.di.AppDatabase
import com.ordersapp.presentation.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(var database: AppDatabase) : ViewModel() {

    private var getAllProducts = MutableStateFlow(true)
    private var product = getAllProducts.flatMapLatest {
        database.productDao().getAllProducts()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(ProductState())
    val state = combine(_state, product, getAllProducts) { state, products, getAllProducts ->
        state.copy(products = products)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProductState())

    fun saveProduct() {
        val product = Product (
            id = state.value.id.value,
            name = state.value.name.value,
            price = state.value.price.value,
            categoryId = state.value.categoryId.value
        )

        viewModelScope.launch {
            database.productDao().upsertProduct(product)
        }

        state.value.id.value = 0
        state.value.name.value = ""
        state.value.price.value= ""
        state.value.categoryId.value = 0

    }

    fun deleteProduct() {
        val product = Product (
            id = state.value.id.value,
            name = state.value.name.value,
            price = state.value.price.value,
            categoryId = state.value.categoryId.value
        )

        viewModelScope.launch {
            database.productDao().deleteAllProduct(product)
        }

        state.value.id.value = 0
        state.value.name.value = ""
        state.value.price.value= ""
        state.value.categoryId.value = 0
    }
}