package com.ordersapp.data.tableproductscrossref

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.ordersapp.data.product.Product
import com.ordersapp.data.table.Table

@Entity(
    tableName = "table_product",
    primaryKeys = ["tableId", "productId"],
    foreignKeys = [
        ForeignKey(entity = Table::class, parentColumns = ["id"], childColumns = ["tableId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Product::class, parentColumns = ["id"], childColumns = ["productId"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index(value = ["productId"])]
)
data class TableProductsCrossRef(
    val tableId: Int,
    val productId: Int,
    val quantity: Int
)
