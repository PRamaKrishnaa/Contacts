package com.ram.kotlin.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "users", indices = [Index(value = ["name"], unique = true)])
class Users(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "number") var number: String

)
