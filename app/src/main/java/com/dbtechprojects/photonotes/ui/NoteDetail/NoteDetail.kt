package com.dbtechprojects.photonotes.ui.NoteDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dbtechprojects.photonotes.R
import com.dbtechprojects.photonotes.model.Note
import com.dbtechprojects.photonotes.test.TestConstants
import com.dbtechprojects.photonotes.ui.NotesList.NotesFab
import com.dbtechprojects.photonotes.ui.theme.PhotoNotesTheme


@Composable
fun NoteDetailScreen(noteId: Int){
    val note = TestConstants.notes.find{note -> note.id == noteId  } ?: Note(note = "Cannot find note details", id = 0, title = "Cannot find note details")
    PhotoNotesTheme{
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    NoteDetailAppBar(
                        title = stringResource(R.string.photo_notes),
                    )
                },
                floatingActionButton = { NotesFab(contentDescription = stringResource(R.string.create_note), action = {}, icon = R.drawable.camera) }

            ) {
                Column(
                    Modifier
                        .background(Color.White)
                        .fillMaxSize()
                ) {
                    Text(
                        text = note.title  ,
                        modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = note.dateUpdated, Modifier.padding(6.dp), color = Color.Gray)
                    Text(text = note.note , Modifier.padding(12.dp))
                }

            }
        }
    }
}

@Composable
fun NoteDetailAppBar(
    title: String,
) {
    TopAppBar(
        title = { Text(title) },
        backgroundColor = MaterialTheme.colors.primary,
        actions = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.edit_note) ,
                contentDescription = stringResource(R.string.edit_note),
                tint = Color.White
            )
        }
    )
}