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
    suspend fun deleteAllProduct(product: Product)

    @Query("SELECT * FROM product")
    fun getAllProducts(): Flow<List<Product>>

    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun productFindById(id: Int): Product

    @Query("SELECT * FROM product WHERE category_id = :categoryId")
    fun findByCategory(categoryId: Int): List<Product>
}