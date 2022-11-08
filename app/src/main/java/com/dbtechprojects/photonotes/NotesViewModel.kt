package com.dbtechprojects.photonotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dbtechprojects.photonotes.model.Note
import com.dbtechprojects.photonotes.persistence.NotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(
    private val db: NotesDao
) : ViewModel() {


     fun getNotes() = db.getNotes()

    fun deleteNote(note: Note) {
        db.deleteNote(note)
    }

    fun updateNote(note: Note) {
        db.updateNote(note)
    }

    fun createNote(title: String, note: String, image: String? = null) {
        val note = Note().apply {
            this.title = title
            this.note = note
            this.imageUri = image
        }
        db.insertNote(note)

    }


     fun getNote(id: String): Note? {
        return db.getNoteById(id)
    }

}

class NoteViewModelFactory(
    private val db: NotesDao
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(
            db = db
        ) as T
    }
}