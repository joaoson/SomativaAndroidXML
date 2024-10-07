package com.example.somativaandroid.recyclerviewpackage
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {
    @Insert
    fun insert(user: User)
    @Query("SELECT * FROM usuarios")
    fun getAll(): List<User>
    @Update
    fun update(user: User)
    @Delete
    fun delete(user: User)
}