package com.ordersapp.data.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ordersapp.data.product.ProductDao
import com.ordersapp.data.product.Product
import com.ordersapp.data.tableproductscrossref.TableProductsCrossRef
import com.ordersapp.data.table.Table
import com.ordersapp.data.table.TableDao
import com.ordersapp.data.tableproductscrossref.TableProductsCrossRefDao

@Database(entities = [Product::class, Table::class, TableProductsCrossRef::class], version = 3, exportSchema = true)
abstract class AppDatabase : RoomDatabase(){
    abstract fun productDao(): ProductDao
    abstract fun tableDao(): TableDao
    abstract fun tableProductsCrossRefDao() : TableProductsCrossRefDao
}