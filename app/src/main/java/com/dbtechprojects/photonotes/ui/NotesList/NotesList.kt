package com.dbtechprojects.photonotes.ui.NotesList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dbtechprojects.photonotes.Constants
import com.dbtechprojects.photonotes.R
import com.dbtechprojects.photonotes.model.Note
import com.dbtechprojects.photonotes.model.getDay
import com.dbtechprojects.photonotes.test.TestConstants
import com.dbtechprojects.photonotes.ui.theme.PhotoNotesTheme
import java.util.*

@Composable
fun NotesList(navController: NavController) {
    val openDialog = remember { mutableStateOf(false) }
    val deleteText = remember { mutableStateOf("") }
    PhotoNotesTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    PhotoNotesAppBar(
                        title = stringResource(R.string.photo_notes), iconContentDescription = stringResource(
                            R.string.delete_note
                        ), openDialog, deleteText
                    )
                },
                floatingActionButton = { NotesFab(contentDescription = stringResource(R.string.create_note), action = {}, icon = R.drawable.note_add_icon) }

            ) {
                NotesList(notes = TestConstants.notes, openDialog, deleteText, navController)
                DeleteDialog(openDialog = openDialog, text = deleteText) {

                }
            }

        }
    }
}

@Composable
fun PhotoNotesAppBar(
    title: String,
    iconContentDescription: String,
    openDialog: MutableState<Boolean>,
    text: MutableState<String>
) {
    TopAppBar(
        title = { Text(title) },
        backgroundColor = MaterialTheme.colors.primary,
        actions = { IconButton(onClick = {
            openDialog.value = true
            text.value = "Are you sure you want to delete all notes ?"
        }) {
            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.note_delete) , contentDescription = iconContentDescription, tint = Color.White )
        }
        }
    )
}

@Composable
fun NotesList(notes: List<Note>,
              openDialog: MutableState<Boolean>,
              text: MutableState<String>,
              navController: NavController) {
    val previousHeader = remember{ mutableStateOf("")}
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
    ) {

        items(notes) { note ->
            if (note.getDay() != previousHeader.value){
                Column(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()) {
                    Text(text = note.getDay(), color = Color.Black)
                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp))
                previousHeader.value = note.getDay()
            }


            NoteListItem(note,openDialog,text, navController)
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(12.dp))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListItem(note: Note, openDialog: MutableState<Boolean>, text: MutableState<String>, navController: NavController){
    return Box(modifier = Modifier.clip(RoundedCornerShape(12.dp))) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .combinedClickable(interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false), // You can also change the color and radius of the ripple
                    onClick = {
                        navController.navigate(Constants.noteDetailNavigation(note.id))
                    },
                    onLongClick = {
                        openDialog.value = true
                        text.value = "Are you sure you want to delete this note ?"
                    }
                )

        ) {
            Text(text = note.title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp))
            Text(text = note.note, maxLines = 3, modifier = Modifier.padding(12.dp))
            Text(text = note.dateUpdated, modifier = Modifier.padding(horizontal = 6.dp))

        }
    }
}

@Composable
fun NotesFab(contentDescription: String, icon: Int, action: () -> Unit) {
    return FloatingActionButton(
        onClick = { action.invoke() },
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(
            ImageVector.vectorResource(id = icon),
            contentDescription = contentDescription ,
            tint = Color.White
        )

    }
}

@Composable
fun DeleteDialog(openDialog: MutableState<Boolean>, text: MutableState<String>, action: () -> Unit){
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Delete Note")
            },
            text = {
                Column() {
                    Text(text.value)
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column() {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { action.invoke() }
                        ) {
                            Text("Yes")
                        }
                        Spacer(modifier = Modifier.padding(12.dp))
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { openDialog.value = false }
                        ) {
                            Text("No")
                        }
                    }

                }
            }
        )
    }
}

