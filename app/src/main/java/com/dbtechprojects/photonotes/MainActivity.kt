package com.dbtechprojects.photonotes

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dbtechprojects.photonotes.model.Note
import com.dbtechprojects.photonotes.test.TestConstants
import com.dbtechprojects.photonotes.ui.theme.PhotoNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = { PhotoNotesAppBar(title = getString(R.string.photo_notes), iconContentDescription = getString(
                                                    R.string.delete_note) )
                        },
                        floatingActionButton = { NotesFab(contentDescription = getString(R.string.create_note))}

                    ) {
                        NotesList(notes = TestConstants.testNotes())
                    }

                }
            }
        }
    }
}

@Composable
fun PhotoNotesAppBar(title: String, iconContentDescription: String) {
    TopAppBar(
        title = { Text(title) },
        backgroundColor = MaterialTheme.colors.primary,
        actions = { IconButton(onClick = { Log.d("hello", "") }) {
            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.note_delete) , contentDescription = iconContentDescription, tint = Color.White )
        }}
    )
}

@Composable
fun NotesList(notes: List<Note>) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
    ) {
        items(notes) { note ->
            NoteListItem(note)
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(12.dp))
        }
    }
}

@Composable
fun NoteListItem(note: Note){
 return Box(modifier = Modifier.clip(RoundedCornerShape(12.dp))) {
     Column(
         modifier = Modifier
             .background(Color.White)
             .fillMaxWidth()
             .clickable(interactionSource = remember { MutableInteractionSource() },
                 indication = rememberRipple(bounded = false), // You can also change the color and radius of the ripple
                 onClick = {})
     ) {
         Text(text = note.title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp))
         Text(text = note.note, maxLines = 3, modifier = Modifier.padding(12.dp))
         Text(text = note.dateCreated, modifier = Modifier.padding(horizontal = 6.dp))

     }
 }
}

@Composable
fun NotesFab(contentDescription: String) {
    return FloatingActionButton(
        onClick = { /*TODO*/ },
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(
            ImageVector.vectorResource(id = R.drawable.note_add_icon),
            contentDescription = contentDescription ,
            tint = Color.White
        )

    }
}