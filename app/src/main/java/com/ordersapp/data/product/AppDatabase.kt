package com.ordersapp.data.product

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(Product::class)], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase(){
    abstract fun Dao(): Dao
}