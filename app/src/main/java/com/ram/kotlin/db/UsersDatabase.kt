package com.ram.kotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Users::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDAO

    companion object {
        private var instance: UsersDatabase? = null

        fun getInstance(context: Context): UsersDatabase? {
            if (instance == null)
                instance = Room.databaseBuilder<UsersDatabase>(
                    context.applicationContext,
                    UsersDatabase::class.java,
                    "Users.db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            return instance!!
        }
    }

    fun destroyInstance() {
        instance = null
    }
}