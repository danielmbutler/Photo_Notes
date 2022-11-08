package com.dbtechprojects.photonotes.persistence

import com.dbtechprojects.photonotes.model.Note
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.Sort

class NoteDBRealm(private val realmConfiguration: RealmConfiguration) : NotesDao {
    override fun getNoteById(id: String): Note? {
          val realm = Realm.getInstance(realmConfiguration)
        return realm.where(Note::class.java).equalTo("id", id).findFirst()
    }

    override fun getNotes(): List<Note> {

        val realm = Realm.getInstance(realmConfiguration)
        val notes = mutableListOf<Note>()
        realm.executeTransaction{ realmTransaction ->
            notes.addAll(realmTransaction
                .where(Note::class.java)
                .findAllSorted("dateUpdated", Sort.DESCENDING)
            )
        }
        return notes
    }

    override fun deleteNote(note: Note) {
        val realm = Realm.getInstance(realmConfiguration)
        realm.executeTransaction { transaction ->
            val noteQuery : RealmResults<Note?> = transaction.where(Note::class.java).equalTo("id", note.id).findAll()
            noteQuery.deleteAllFromRealm()
        }
    }

    override fun updateNote(note: Note) {
        insertNote(note)
    }

    override fun insertNote(note: Note) {
        val realm = Realm.getInstance(realmConfiguration)
        realm.executeTransaction { transaction ->
            transaction.insertOrUpdate(note)
        }
    }
}