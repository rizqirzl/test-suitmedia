package com.suitmedia.suitmediaapp.api

import com.suitmedia.suitmediaapp.model.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): UsersResponse

}