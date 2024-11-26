package com.ordersapp.data.product

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {

    @Insert
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Query("DELETE FROM product WHERE id =:id")
    fun delete(id: Int)

    @Query("SELECT * FROM product")
    fun all(): LiveData<List<Product>>

    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun findById(id: Int): Product
}