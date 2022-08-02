package com.dbtechprojects.photonotes.ui.EditNote

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dbtechprojects.photonotes.R
import com.dbtechprojects.photonotes.model.Note
import com.dbtechprojects.photonotes.test.TestConstants
import com.dbtechprojects.photonotes.ui.GenericAppBar
import com.dbtechprojects.photonotes.ui.NotesList.NotesFab
import com.dbtechprojects.photonotes.ui.theme.PhotoNotesTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteEditScreen(noteId: Int, navController: NavController){
    val note = TestConstants.notes.find{ note -> note.id == noteId  } ?: Note(note = "Cannot find note details", id = 0, title = "Cannot find note details")

    val currentNote = remember{ mutableStateOf(note.note)}
    val currentTitle = remember{ mutableStateOf(note.title)}
    val saveButtonState = remember{ mutableStateOf(false)}
    PhotoNotesTheme{
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    GenericAppBar(
                        title = "Edit Note",
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.save),
                                contentDescription = stringResource(R.string.save_note),
                                tint = Color.White,
                            )
                        },
                        onIconClick = { navController.popBackStack()  },
                        iconState = saveButtonState
                    )
                },
                content = {

                    Column(
                        Modifier
                            .background(Color.White)
                            .padding(12.dp)
                            .fillMaxSize()
                    ) {
                        TextField(
                            value = currentTitle.value,
                            onValueChange = {value ->
                                currentTitle.value = value
                                if (currentTitle.value != note.title){
                                    saveButtonState.value = true
                                } else if (currentNote.value == note.note &&
                                        currentTitle.value == note.title){
                                    saveButtonState.value = false
                                }
                                            } ,
                            label = {Text(text = "Title")} 
                        )
                        Spacer(modifier = Modifier.padding(12.dp))
                        TextField(
                            value = currentNote.value ,
                            onValueChange = {value ->
                                currentNote.value = value
                                if (currentNote.value != note.note){
                                    saveButtonState.value = true
                                } else if (currentNote.value == note.note &&
                                    currentTitle.value == note.title){
                                    saveButtonState.value = false
                                }
                                            } ,
                            label = {Text(text = "Body")}
                        )
                    }
                }

            )
        }
    }
}