package com.dbtechprojects.photonotes.persistence
import androidx.lifecycle.LiveData
import androidx.room.*
import com.dbtechprojects.photonotes.model.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes WHERE notes.id=:id")
    suspend fun getNoteById(id: Int) : Note?

    @Query("SELECT * FROM Notes ORDER BY dateUpdated DESC")
    fun getNotes() : LiveData<List<Note>>

    @Delete
    fun deleteNote(note: Note) : Int

    @Update
    fun updateNote(note: Note) : Int

    @Insert
    fun insertNote(note: Note)

}