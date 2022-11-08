package com.dbtechprojects.photonotes

import com.dbtechprojects.photonotes.model.Note

object Constants {

    val noteDetailPlaceHolder = Note().apply {
        id = "0"
        note = "Cannot find note details"
        isPlaceholder = true
        title = "Cannot find note details"
    }


    const val NAVIGATION_NOTES_CREATE = "noteCreate"
    const val NAVIGATION_NOTES_LIST = "noteList"
    const val NAVIGATION_NOTES_DETAIL = "noteDetail/{noteId}"
    const val NAVIGATION_NOTES_EDIT = "noteEdit/{noteId}"
    const val NAVIGATION_NOTES_ID_ARGUMENT = "noteId"

    fun noteDetailNavigation(noteId : String) = "noteDetail/$noteId"
    fun noteEditNavigation(noteId : String) = "noteEdit/$noteId"
}