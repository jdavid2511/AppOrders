package com.ordersapp.data.product

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.ordersapp.data.table.Table

@Entity(
    tableName = "table_product",
    primaryKeys = ["tableId", "productId"],
    foreignKeys = [
        ForeignKey(entity = Table::class, parentColumns = ["id"], childColumns = ["mesaId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Product::class, parentColumns = ["id"], childColumns = ["productoId"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index(value = ["productId"])]
)
data class TableProductsCrossRef(
    val tableId: Int,
    val product: Int
)
