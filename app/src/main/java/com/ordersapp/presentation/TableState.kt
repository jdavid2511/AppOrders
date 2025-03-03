package com.ordersapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ordersapp.data.table.Table

data class TableState (
    val tables: List<Table> = emptyList(),
    val name : MutableState<Int> = mutableStateOf(0),
    val total : MutableState<Long> = mutableStateOf(0),
    )