package com.example.caloriekotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(val context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "users", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE users (id INTEGER PRIMARY KEY, login TEXT, email TEXT, pass TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun addUser(user: User) {
        val values = ContentValues().apply {
            put("login", user.login)
            put("email", user.email)
            put("pass", user.pass)
            put("height", user.height)
            put("weight", user.weight)
            put("gender", user.gender)
            put("activityLevel", user.activityLevel)
        }

        val db = this.writableDatabase
        db.insert("users", null, values)
        db.close()
    }

    fun getUser(login: String, pass: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM users WHERE login = ? AND pass = ?"
        val cursor = db.rawQuery(query, arrayOf(login, pass))

        val isUserFound = cursor.moveToFirst()

        cursor.close()
        db.close()

        return isUserFound
    }
}