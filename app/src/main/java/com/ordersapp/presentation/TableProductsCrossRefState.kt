package com.ordersapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ordersapp.data.tableproductscrossref.TableProductsCrossRef

data class TableProductsCrossRefState(
    val items: List<TableProductsCrossRef> = emptyList(),
    val tableId: MutableState<Int> = mutableStateOf(0),
    val productId: MutableState<Int> = mutableStateOf(0),
    val isLoading: Boolean = false,
    val error: String? = null
)