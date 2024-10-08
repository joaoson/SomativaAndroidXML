package com.example.somativaandroid.recyclerviewpackage
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var email: String,
    var senha: String
)
