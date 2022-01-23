package com.nyx.kls_notepad.repository

import com.nyx.kls_notepad.entity.Note

interface Repository {

    fun addNote(note: Note)
    fun getAllNotes(): ArrayList<Note>
    fun getNote(id: Int): Note?
    fun deleteNote(id: Int)
}