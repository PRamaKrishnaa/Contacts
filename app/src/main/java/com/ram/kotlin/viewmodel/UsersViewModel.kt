package com.ram.kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ram.kotlin.db.Users
import com.ram.kotlin.db.UsersDatabase

class UsersViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var allUsers: MutableLiveData<List<Users>>
    val userDao = UsersDatabase.getInstance((getApplication()))?.usersDao()

    init {
        allUsers = MutableLiveData()
        getAllUsers()
    }

    fun getAllUsersObserver(): MutableLiveData<List<Users>> {
        return allUsers

    }


    fun getAllUsers() {
        val list = userDao?.getAllUsers()

        allUsers.postValue(list)

    }

    fun insertUser(users: Users) {

        userDao?.insertData(users)

        getAllUsers()
    }

    fun updateUser(users: Users) {

        userDao?.updateData(users)

        getAllUsers()
    }

    fun deleteUser(users: Users) {
        userDao?.deleteData(users)

        getAllUsers()
    }

}


