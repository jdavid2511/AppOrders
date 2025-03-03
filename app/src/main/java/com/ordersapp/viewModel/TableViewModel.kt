package com.ordersapp.viewModel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ordersapp.data.di.AppDatabase
import com.ordersapp.data.product.Product
import com.ordersapp.data.table.Table
import com.ordersapp.data.tableproductscrossref.TableProductsCrossRef
import com.ordersapp.presentation.TableState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    fun saveTableWithProducts(table: Table, productId: Int) {
        viewModelScope.launch {
            database.tableDao().insertTableWithProducts(table, productId)
        }
    }

    fun updateTable(table: Table) {
        viewModelScope.launch(Dispatchers.IO) {
            database.tableDao().updateTable(table)
        }
    }

    fun updateTableCrossRef(tableProductsCrossRef: TableProductsCrossRef) {
        viewModelScope.launch(Dispatchers.IO) {
            database.tableDao().updateTableCrossRef(tableProductsCrossRef)
        }
    }

    suspend fun checkTable(id: String): Boolean {
        val result = withContext(Dispatchers.IO) {
            database.tableDao().checkTableExists(id = id)
        }
        return result == 0
    }

    fun getTotalByTableId(tableId: Int): Flow<Long> {
        return database.tableDao().getTotalByTableId(tableId)
    }
}