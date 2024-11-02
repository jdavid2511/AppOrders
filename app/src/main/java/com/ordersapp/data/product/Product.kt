package com.ordersapp.data.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.ordersapp.data.category.Category

@Entity(tableName = "Product",
    foreignKeys = [ ForeignKey(
        entity = Category::class,
        parentColumns = ["id"],
        childColumns = ["category_id"],
        onDelete = ForeignKey.CASCADE)])
data class Product (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "text")
    var text: String,

    @ColumnInfo(name = "category_id")
    var category: Category
)
