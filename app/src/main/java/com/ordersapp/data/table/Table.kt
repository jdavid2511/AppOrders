package com.ordersapp.data.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.ordersapp.data.product.Product

@Entity(tableName = "Table")
data class Table(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "total")
    val total: Long,
)
