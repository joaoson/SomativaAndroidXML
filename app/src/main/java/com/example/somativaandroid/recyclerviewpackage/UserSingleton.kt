package com.example.somativaandroid.recyclerviewpackage

import android.content.Context

object UserSingleton {
    lateinit var userList: List<User>
    private lateinit var dao: UserDAO
    fun setContext(context: Context){
        userDatabase.getInstance(context)?.let {
            dao = it.UserDAO()
            userList = dao.getAll()
        }
    }
    fun addUser(user: User) {
        dao.insert(user)
        userList = dao.getAll()
    }
    fun updateUser(user: User) {
        dao.update(user)
        userList = dao.getAll()
    }
    fun deleteUser(user: User) {
        dao.delete(user)
        userList = dao.getAll()
    }
}
