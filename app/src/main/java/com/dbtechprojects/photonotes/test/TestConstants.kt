package com.dbtechprojects.photonotes.test

import com.dbtechprojects.photonotes.model.Note

object TestConstants {
    fun testNotes(): List<Note>{
        val notes = mutableListOf<Note>()
        for (i in 0..10){
            notes.add(
                Note(id = i, title = "Note $i", note = "This is note $i")
            )
        }
        return  notes
    }
}