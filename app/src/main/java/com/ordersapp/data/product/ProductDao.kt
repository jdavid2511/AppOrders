package com.ordersapp.data.product

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Upsert
    suspend fun upsertProduct(product: Product)

    @Update
    fun update(product: Product)

    @Delete
    fun deleteAllProduct(product: Product)

    @Query("DELETE FROM product WHERE id =:id")
    fun deleteProduct(id: Int)

    @Query("SELECT * FROM product")
    fun getAllProducts(): Flow<List<Product>>

    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun findById(id: Int): Product
}