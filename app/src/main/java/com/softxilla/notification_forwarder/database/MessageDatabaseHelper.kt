package com.softxilla.notification_forwarder.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DATABASE_NAME = "notifications"
const val TABLE_NAME = "text_messages"

class MessageDatabaseHelper(
    context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, 4) {
    companion object {
        const val ID = "id"
        const val APP_NAME = "app_name"
        const val PACKAGE_NAME = "package_name"
        const val ANDROID_TITLE = "android_title"
        const val ANDROID_TEXT = "android_text"
        const val CREATED_AT = "created_at"
        const val STATUS = "status"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val sql =
            ("CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $APP_NAME TEXT, $PACKAGE_NAME TEXT, $ANDROID_TITLE TEXT, $ANDROID_TEXT TEXT, $CREATED_AT timestamp default current_timestamp, $STATUS INTEGER default 0)")
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(sql)
        onCreate(db)
    }

    fun storeMessagesSQLite(
        appName: String,
        packageName: String,
        androidTitle: String,
        androidText: String
    ): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put(APP_NAME, appName)
        values.put(PACKAGE_NAME, packageName)
        values.put(ANDROID_TITLE, androidTitle)
        values.put(ANDROID_TEXT, androidText)
        db.insert(TABLE_NAME, null, values)
        db.close()
        return true
    }

    fun getUnSyncedMessage(): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $STATUS = 0", null)
    }
}