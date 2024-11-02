package com.ordersapp.data.category

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesRepository(private val categoriesDao: CategoriesDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insert(Category: Category) {
        coroutineScope.launch(Dispatchers.IO) {
            categoriesDao.insert(Category)
        }
    }

    fun update(Category: Category) {
        coroutineScope.launch(Dispatchers.IO) {
            categoriesDao.update(Category)
        }
    }

    fun all(): LiveData<List<Category>> {
        return categoriesDao.all()
    }

    suspend fun findById(id: Int): Category {
        return categoriesDao.findById(id)
    }

    fun delete(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            categoriesDao.delete(id)
        }
    }
}