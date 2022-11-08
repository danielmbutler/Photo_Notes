package com.dbtechprojects.photonotes

import android.app.Application
import android.content.Intent
import android.net.Uri
import com.dbtechprojects.photonotes.persistence.NoteDBRealm
import com.dbtechprojects.photonotes.persistence.NotesDao
import io.realm.Realm
import io.realm.RealmConfiguration

class PhotoNotesApp : Application() {
    private var db : RealmConfiguration? = null

    init {
        instance = this
    }

    private fun getDb() : RealmConfiguration {
        return if (db != null){
            db!!
        } else {
            Realm.init(instance!!.applicationContext)
            db = RealmConfiguration.Builder()
                .schemaVersion(1)
                .build()
            Realm.setDefaultConfiguration(db)
            db!!
        }
    }

    companion object {
        private var instance: PhotoNotesApp? = null

        fun getDao() : NotesDao {
            return NoteDBRealm(instance!!.getDb())
        }

        fun getUriPermission(uri: Uri){
            instance!!.applicationContext.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
    }
}