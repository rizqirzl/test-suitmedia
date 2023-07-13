package com.suitmedia.suitmediaapp.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.suitmedia.suitmediaapp.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(story: List<User>)

    @Query("SELECT * FROM user")
    fun getAllUser(): PagingSource<Int, User>

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}