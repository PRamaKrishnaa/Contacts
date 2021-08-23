package com.ram.kotlin.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface UsersDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(vararg users: Users?)

    @Update
    fun updateData(vararg users: Users?)

    @Delete
    fun deleteData(users: Users?)

    @Query("DELETE FROM users")
    fun deleteAllUsers()

    @Query("SELECT * FROM users ORDER BY name ASC")
    fun getAllUsers(): List<Users>?

    @Query("SELECT * FROM users WHERE id LIKE :id ")
    fun getData(id: Int): Users
}