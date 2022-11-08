package com.dbtechprojects.photonotes.ui.NoteCreate

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.dbtechprojects.photonotes.NotesViewModel
import com.dbtechprojects.photonotes.PhotoNotesApp
import com.dbtechprojects.photonotes.R
import com.dbtechprojects.photonotes.ui.GenericAppBar
import com.dbtechprojects.photonotes.ui.NoteList.NotesFab
import com.dbtechprojects.photonotes.ui.theme.PhotoNotesTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreateNoteScreen(
    navController: NavController,
    viewModel: NotesViewModel
) {

    val currentNote = remember {
        mutableStateOf("")
    }
    val currentTitle = remember {
        mutableStateOf("")
    }
    val currentPhotos = remember {
        mutableStateOf("")
    }
    val saveButtonState = remember {
        mutableStateOf(false)
    }

    val getImageRequest =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenDocument(),
            onResult = { uri ->
                if (uri != null) {
                    PhotoNotesApp.getUriPermission(uri)
                }
                currentPhotos.value = uri.toString()
            })

    PhotoNotesTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    GenericAppBar(
                        title = "Create Note",
                        onIconClick = {
                            viewModel.createNote(
                              currentTitle.value,
                              currentNote.value,
                              currentPhotos.value
                            )
                            navController.popBackStack()
                        },
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.save),
                                contentDescription = stringResource(id = R.string.save_note),
                                tint = Color.Black
                            )
                        },
                        iconState = saveButtonState
                    )
                },
                floatingActionButton = {
                    NotesFab(
                        contentDescription = stringResource(id = R.string.add_photo),
                        action = {
                            getImageRequest.launch(arrayOf("image/*"))
                        },
                        icon = R.drawable.camera
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxSize()
                ) {
                    if (currentPhotos.value.isNotEmpty()) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest
                                    .Builder(LocalContext.current)
                                    .data(data = Uri.parse(currentPhotos.value))
                                    .build()
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxHeight(0.3f)
                                .fillMaxWidth()
                                .padding(6.dp),

                            contentScale = ContentScale.Crop
                        )
                    }

                    TextField(
                        value = currentTitle.value,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = Color.Black,
                            focusedLabelColor = Color.Black
                        ),
                        onValueChange = { value ->
                            currentTitle.value = value
                            saveButtonState.value = currentTitle.value != "" && currentNote.value != ""
                        },
                        label = { Text("Title") }
                    )
                    Spacer(modifier = Modifier.padding(12.dp))
                    TextField(
                        value = currentNote.value,
                        modifier = Modifier
                            .fillMaxHeight(0.5f)
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = Color.Black,
                            focusedLabelColor = Color.Black
                        ),
                        onValueChange = { value ->
                            currentNote.value = value

                            saveButtonState.value = currentTitle.value != "" && currentNote.value != ""
                        },
                        label = { Text("Body") }
                    )
                }
            }
        }
    }


}
















