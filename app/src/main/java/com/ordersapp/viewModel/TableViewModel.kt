package com.ordersapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ordersapp.data.di.AppDatabase
import com.ordersapp.data.table.Table
import com.ordersapp.presentation.TableState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(var database: AppDatabase) : ViewModel() {

    private var getAllTables = MutableStateFlow(true)
    private var table = getAllTables.flatMapLatest {
        database.tableDao().getAllTables()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(TableState())
    val state = combine(_state, table, getAllTables) { state, tables, getAllTables ->
        state.copy(tables = tables)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TableState())

    fun saveTable() {
        val table = Table (
            name = state.value.name.value,
            total = state.value.total.value
        )

        viewModelScope.launch {
            database.tableDao().upsertTable(table)
        }

        state.value.name.value = ""
        state.value.total.value = 0
    }
}