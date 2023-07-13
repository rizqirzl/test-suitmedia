package com.suitmedia.suitmediaapp.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.suitmedia.suitmediaapp.api.ApiService
import com.suitmedia.suitmediaapp.database.UserDatabase
import com.suitmedia.suitmediaapp.model.User

class UserRepository(private val userDatabase: UserDatabase, private val apiService: ApiService) {

    fun getUser(): LiveData<PagingData<User>> {

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = UserRemoteMediator(userDatabase, apiService),
            pagingSourceFactory = {
                userDatabase.userDao().getAllUser()
            }
        ).liveData
    }

}