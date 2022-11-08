package com.dbtechprojects.photonotes.persistence

import androidx.room.*
import com.dbtechprojects.photonotes.model.Note


interface NotesDao {

    fun getNoteById(id: String): Note?


    fun getNotes() : List<Note>


    fun deleteNote(note: Note)


    fun updateNote(note: Note)


    fun insertNote(note: Note)
}