package com.example.somativaandroid.recyclerviewpackage
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class userDatabase :RoomDatabase() {
    abstract fun UserDAO(): UserDAO
    companion object {
        const val DATABASE_NAME = "user_database"
        private var instance: userDatabase? = null
        fun getInstance(context: Context): userDatabase? {
            if (instance == null){
                synchronized(userDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        userDatabase::class.java,
                        DATABASE_NAME
                    ).allowMainThreadQueries().build()
                }
            }
            return instance

        }
    }
}