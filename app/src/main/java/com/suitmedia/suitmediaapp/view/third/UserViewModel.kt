package com.suitmedia.suitmediaapp.view.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.suitmedia.suitmediaapp.data.UserRepository
import com.suitmedia.suitmediaapp.model.User

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    init {
        getUsers()
    }

    fun getUsers(): LiveData<PagingData<User>> {
        return userRepository.getUser().cachedIn(viewModelScope)
    }
}