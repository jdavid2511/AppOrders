package com.ordersapp.data.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.ordersapp.data.table.Table

@Entity(tableName = "Product")
data class Product (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "price")
    var price: String,

    @ColumnInfo(name = "category_id")
    var categoryId: Int
)
