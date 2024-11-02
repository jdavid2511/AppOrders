package com.ordersapp.data.category

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CategoriesDao {

    @Insert
    fun insert(category: Category)

    @Update
    fun update(category: Category)

    @Query("DELETE FROM category WHERE id =:id")
    fun delete(id: Int)

    @Query("SELECT * FROM category")
    fun all(): LiveData<List<Category>>

    @Query("SELECT * FROM category WHERE id = :id")
    suspend fun findById(id: Int): Category
}