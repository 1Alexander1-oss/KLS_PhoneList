package com.nyx.kls_notepad.database

import android.provider.BaseColumns

object PhoneDB {
    const val TABLE_NAME = "phones"
    const val COLUMN_NAME_PHONES = "models"
    const val COLUMN_NAME_PARAMETERS = "parameters"
    const val COLUMN_NAME_PRICE = "price"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "PhonesModels"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "$COLUMN_NAME_PHONES TEXT, " +
            "$COLUMN_NAME_PARAMETERS TEXT, " +
            "$COLUMN_NAME_PRICE REAL" + ")"
    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

}