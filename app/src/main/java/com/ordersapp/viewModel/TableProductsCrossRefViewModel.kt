package com.ordersapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ordersapp.data.di.AppDatabase
import com.ordersapp.data.tableproductscrossref.TableProductsCrossRef
import com.ordersapp.presentation.TableProductsCrossRefState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableProductsCrossRefViewModel @Inject constructor(var database: AppDatabase) : ViewModel() {

    private var getAllItems = MutableStateFlow(true)
    private val tableProductsCrossRef = getAllItems.flatMapLatest {
        database.tableProductsCrossRefDao().getAllItems()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    val _state = MutableStateFlow(TableProductsCrossRefState())
    val state = combine(_state, tableProductsCrossRef, getAllItems) { state, items, getAllItems ->
        state.copy(items = items)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TableProductsCrossRefState())

    fun saveItem() {
        val tableProductsCrossRef = TableProductsCrossRef(
            tableId = state.value.tableId.value,
            productId = state.value.productId.value
        )

        viewModelScope.launch {
            database.tableProductsCrossRefDao().insert(tableProductsCrossRef)
        }

        state.value.tableId.value = 0
        state.value.productId.value = 0
    }
}