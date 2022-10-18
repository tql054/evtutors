package com.intern.evtutors.di

import android.content.Context
import androidx.room.Room
import com.intern.evtutors.data.database.AppDatabase
import com.intern.evtutors.data.database.daos.CustomerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(context,AppDatabase::class.java,"app_db2").build()
    }

    @Provides
    fun provideCustomerDao(appDatabase: AppDatabase): CustomerDao{
        return appDatabase.customerDao()
    }
}