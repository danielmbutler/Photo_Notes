package com.dbtechprojects.photonotes.test

import com.dbtechprojects.photonotes.model.Note

object TestConstants {
    private fun testNotes(): List<Note>{
        val notes = mutableListOf<Note>()
        val alphabet = listOf(
            'a',
            'b',
            'c',
            'd',
            'e',
            'f',
            'g',
            'h',
            'i',
            'j',
            'k',
            'l',
            'm',
            'n',
            'o',
            'p',
            'q',
            'r',
            's',
            't',
            'u',
            'v',
            'w',
            'x',
            'y',
            'z'
        )
        for (i in 0..20){
            notes.add(
                Note(id = i, title = "${alphabet.get(i)} Note $i ", note = "This is note $i \n" +
                        " \n" + alphabet.joinToString().repeat(20))
            )
            notes.add(
                Note(id = i, title = "${alphabet.get(i)} Note $i . 1", note = "This is note $i")
            )
        }
        return  notes.sortedBy { note: Note -> note.dateUpdated  }
    }
    val notes = testNotes()
}