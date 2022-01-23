package com.nyx.kls_notepad.repository

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.nyx.kls_notepad.database.DBHelper
import com.nyx.kls_notepad.database.PhoneDB
import com.nyx.kls_notepad.entity.Note

class SqlRepository(dbHalper: DBHelper) : Repository {
    private val database: SQLiteDatabase? = dbHalper.writableDatabase
    private lateinit var contentValues: ContentValues

    override fun addNote(note: Note) {
        val title = note.models
        val description = note.parameters
        val price = note.price
        contentValues = ContentValues()
        contentValues.put(PhoneDB.COLUMN_NAME_PHONES, title)
        contentValues.put(PhoneDB.COLUMN_NAME_PARAMETERS, description)
        contentValues.put(PhoneDB.COLUMN_NAME_PRICE, price)

        database?.insert(PhoneDB.TABLE_NAME, null, contentValues)
    }

    override fun getAllNotes(): ArrayList<Note> {
        val notes: ArrayList<Note> = arrayListOf()
        val cursor: Cursor? =
            database?.query(
                PhoneDB.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
            )
        cursor ?: return arrayListOf()

        while (cursor.moveToNext()) {
            var title = ""
            var description = ""
            var id = 1
            var price = 0.1
            var titleIndex = cursor.getColumnIndex(PhoneDB.COLUMN_NAME_PHONES)

            if (titleIndex >= 0) {
                title = cursor.getString(titleIndex)
            }
            val descriptionIndex = cursor.getColumnIndex(PhoneDB.COLUMN_NAME_PARAMETERS)

            if (descriptionIndex >= 0) {
                description = cursor.getString(descriptionIndex)
            }
            val idIndex = cursor.getColumnIndex(BaseColumns._ID)
            if (idIndex >= 0) {
                id = cursor.getInt(idIndex)
            }
            val priceIndex = cursor.getColumnIndex(PhoneDB.COLUMN_NAME_PRICE)
            if (priceIndex >= 0) {
                price = cursor.getDouble(priceIndex)
            }

            val note = Note(id, title, description, price)
            notes.add(note)
        }
        cursor.close()
        return notes
    }

    override fun getNote(id: Int): Note? {
        val selectQuery = "SELECT * FROM ${PhoneDB.TABLE_NAME} WHERE ${BaseColumns._ID} = ?"
        val cursor: Cursor? =
            database?.rawQuery(selectQuery, arrayOf("$id")).apply {
                this ?: return null
                if (moveToFirst()) {
                    return Note(
                        getInt(getColumnIndex(BaseColumns._ID) ?: 0),
                        getString(getColumnIndex(PhoneDB.COLUMN_NAME_PHONES)?: 0),
                        getString(getColumnIndex(PhoneDB.COLUMN_NAME_PARAMETERS)?: 0),
                        getDouble(getColumnIndex(PhoneDB.COLUMN_NAME_PRICE)?: 0)
                    )
                }
            }.also { it?.close()}
        return null
    }
    override fun  deleteNote(id: Int) {
        database?.delete(
            PhoneDB.TABLE_NAME,
            "${BaseColumns._ID} = ?",
            Array(1) { return@Array "$id"}
        )
    }
}
