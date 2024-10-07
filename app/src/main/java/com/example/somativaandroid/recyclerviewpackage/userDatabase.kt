package com.example.somativaandroid.recyclerviewpackage
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [User::class], version = 2, exportSchema = false)
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
                    ).allowMainThreadQueries().addMigrations(MIGRATION_1_2).build()
                }
            }
            return instance

        }

    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            // Example: Add a new column to an existing table
            db.execSQL("ALTER TABLE usuarios ADD COLUMN username TEXT NOT NULL DEFAULT ' '")
        }
    }
    }
}