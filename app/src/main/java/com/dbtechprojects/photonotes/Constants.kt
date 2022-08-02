package com.dbtechprojects.photonotes

object Constants {
const val NAVIGATION_NOTES_LIST = "notesList"
const val NAVIGATION_NOTE_DETAIL = "noteDetail/{noteId}"
const val NAVIGATION_NOTE_EDIT= "noteEdit/{noteId}"
const val NAVIGATION_NOTE_ID_Argument = "noteId"
const val TABLE_NAME = "Notes"
    fun noteDetailNavigation(noteId : Int) = "noteDetail/$noteId"
    fun noteEditNavigation(noteId : Int) = "noteEdit/$noteId"
}