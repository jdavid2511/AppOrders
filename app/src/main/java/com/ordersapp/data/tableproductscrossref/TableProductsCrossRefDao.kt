package com.ordersapp.data.tableproductscrossref

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TableProductsCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tableProductsCrossRef: TableProductsCrossRef)

    @Query("SELECT * FROM table_product")
    fun getAllItems(): Flow<List<TableProductsCrossRef>>
}