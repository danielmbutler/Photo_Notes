package com.dbtechprojects.photonotes

object Constants {
const val NAVIGATION_NOTES_LIST = "notesList"
const val NAVIGATION_NOTE_DETAIL = "noteDetail/{noteId}"
const val NAVIGATION_NOTE_DETAIL_Argument = "noteId"
    fun noteDetailNavigation(noteId : Int) = "noteDetail/$noteId"
}