package com.ordersapp.data.product

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsRepository(private val productsDao: ProductDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insert(Product: Product) {
        coroutineScope.launch(Dispatchers.IO) {
            productsDao.insert(Product)
        }
    }

    fun update(Product: Product) {
        coroutineScope.launch(Dispatchers.IO) {
            productsDao.update(Product)
        }
    }

    fun all(): LiveData<List<Product>> {
        return productsDao.all()
    }

    suspend fun findById(id: Int): Product {
        return productsDao.findById(id)
    }

    fun delete(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            productsDao.delete(id)
        }
    }
}