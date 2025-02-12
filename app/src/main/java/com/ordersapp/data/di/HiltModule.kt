package com.ordersapp.data.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    @Provides
    fun providesDatabase(application: Application) : AppDatabase {
        return Room.databaseBuilder(
            application.baseContext,
            AppDatabase::class.java,
            "ordersappDB"
        ).fallbackToDestructiveMigration().build()
    }
}