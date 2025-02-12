package com.ordersapp.data.table

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ordersapp.data.product.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface TableDao {

    @Upsert
    suspend fun upsertTable(table: Table)

    @Query("SELECT * FROM tables")
    fun getAllTables(): Flow<List<Table>>

}