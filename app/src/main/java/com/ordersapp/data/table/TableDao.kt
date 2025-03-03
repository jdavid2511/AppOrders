package com.ordersapp.data.table

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.ordersapp.data.tableproductscrossref.TableProductsCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface TableDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTableProduct(crossRef: TableProductsCrossRef)

    @Upsert
    suspend fun upsertTable(table: Table)

    @Query("SELECT * FROM tables")
    fun getAllTables(): Flow<List<Table>>

    @Query("SELECT * FROM table_product WHERE tableId = :tableId")
    fun getAllItems(tableId: String?): List<TableProductsCrossRef>

    @Update
    fun updateTable(table: Table)

    @Query("SELECT COUNT(*) FROM tables WHERE id = :id")
    fun checkTableExists(id: String?): Int

    @Transaction
    suspend fun insertTableWithProducts(table: Table, productId: Int) {

        if (checkTableExists(table.id.toString()) == 0) {
            upsertTable(table)
        } else {
            updateTable(table)
        }
        // Verifica si el producto ya está en la mesa
        val existingProduct = getTableProduct(table.id, productId)

        if (existingProduct != null) {
            // Si ya existe, aumenta la cantidad y actualiza el total
            updateTableProductQuantity(table.id, productId, existingProduct.quantity + 1)
        } else {
            // Si no existe, lo inserta con cantidad inicial de 1
            val crossRef = TableProductsCrossRef(tableId = table.id, productId = productId, quantity = 1)
            insertTableProduct(crossRef)
        }
    }

    @Query("SELECT COALESCE(SUM(total), 0) FROM tables WHERE id = :tableId")
    fun getTotalByTableId(tableId: Int): Flow<Long>

    @Query("SELECT * FROM table_product WHERE tableId = :tableId AND productId = :productId")
    fun getTableProduct(tableId: Int, productId: Int): TableProductsCrossRef?

    @Query("UPDATE table_product SET quantity = :newQuantity WHERE tableId = :tableId AND productId = :productId")
    suspend fun updateTableProductQuantity(tableId: Int, productId: Int, newQuantity: Int)

    @Update
    fun updateTableCrossRef(tableProductsCrossRef: TableProductsCrossRef)

    @Query("DELETE FROM table_product WHERE tableId = :tableId")
    suspend fun deleteAllProducts(tableId: Int)

    @Query("UPDATE tables SET total = 0 WHERE id = :tableId")
    suspend fun resetTableTotal(tableId: Int)

    @Query("DELETE FROM table_product WHERE tableId= :tableId AND productId= :productId")
    suspend fun deleteProduct(tableId: Int, productId: Int)

    @Query("UPDATE tables SET total = total - :productPrice WHERE id = :tableId")
    suspend fun updateTableTotalAfterDeletion(tableId: Int, productPrice: Long)

}