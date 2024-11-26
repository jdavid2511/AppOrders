package com.ordersapp.data.category

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ordersapp.data.Converters

@Database(entities = [(Category::class)], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CategoryDatabase : RoomDatabase(){
    abstract fun cateroryDao() : CategoriesDao

    companion object {

        @Volatile
        private var INSTANCE: CategoryDatabase? = null

        fun getInstance(context: Context): CategoryDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CategoryDatabase::class.java,
                        "category"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}