package com.ordersapp.data.table

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ordersapp.data.product.Product
import com.ordersapp.data.product.TableProductsCrossRef

data class TableProducts(
    @Embedded
    val table: Table,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(TableProductsCrossRef::class)
    )
    val products: List<Product>
)
