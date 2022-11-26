package com.dbtechprojects.photonotes.ui

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.*
import com.dbtechprojects.photonotes.model.Note
import com.dbtechprojects.photonotes.persistence.NotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private object PreferencesKeys {
    val NAME = stringPreferencesKey("name")
}

class NotesViewModel(
    private val db: NotesDao,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    val notes: LiveData<List<Note>> = db.getNotes()


     fun deleteNotes(note: Note) {
        viewModelScope.launch(Dispatchers.IO){
            db.deleteNote(note)
        }
    }

    val nameFlow: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.NAME]
        }

    // save name
     fun saveName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.edit { settings ->
                settings[PreferencesKeys.NAME] = name
            }
        }
    }

     fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO){
            db.updateNote(note)
        }
    }

     fun createNote(title: String, note: String, image: String? = null) {
        viewModelScope.launch(Dispatchers.IO){
            db.insertNote(Note(title = title, note = note, imageUri = image))
        }
    }

     suspend fun getNote(noteId : Int) : Note? {
        return db.getNoteById(noteId)
    }

}

class NotesViewModelFactory(
    private val db: NotesDao,
    private val dataStore: DataStore<Preferences>
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  NotesViewModel(
            db = db,
            dataStore = dataStore
        ) as T
    }

}