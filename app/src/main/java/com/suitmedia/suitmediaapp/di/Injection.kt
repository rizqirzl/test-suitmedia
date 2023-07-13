package com.suitmedia.suitmediaapp.di

import android.content.Context
import com.suitmedia.suitmediaapp.api.ApiConfig
import com.suitmedia.suitmediaapp.data.UserRepository
import com.suitmedia.suitmediaapp.database.UserDatabase

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val database = UserDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return UserRepository(database, apiService)
    }
}