package com.ordersapp.viewModel

import androidx.lifecycle.ViewModel
import com.ordersapp.data.di.AppDatabase
import com.ordersapp.data.tableproductscrossref.TableProductsCrossRef
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class TableProductsCrossRefViewModel @Inject constructor(var database: AppDatabase) : ViewModel() {

    fun getAllItems(tableId: String?): Flow<List<TableProductsCrossRef>> = flow {
        emit(database.tableDao().getAllItems(tableId = tableId))
    }.flowOn(Dispatchers.IO)
}