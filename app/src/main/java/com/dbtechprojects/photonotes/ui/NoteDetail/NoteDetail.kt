package com.dbtechprojects.photonotes.ui.NoteDetail

import android.annotation.SuppressLint
import android.net.Uri
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.dbtechprojects.photonotes.Constants
import com.dbtechprojects.photonotes.Constants.noteDetailPlaceHolder
import com.dbtechprojects.photonotes.R
import com.dbtechprojects.photonotes.ui.GenericAppBar
import com.dbtechprojects.photonotes.ui.NotesViewModel
import com.dbtechprojects.photonotes.ui.theme.PhotoNotesTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteDetailScreen(noteId: Int, navController: NavController, viewModel: NotesViewModel) {
    val scope = rememberCoroutineScope()
    val note = remember {
        mutableStateOf(noteDetailPlaceHolder)
    }


    LaunchedEffect(true) {
        scope.launch(Dispatchers.IO) {
            note.value = viewModel.getNote(noteId) ?: noteDetailPlaceHolder
        }
    }

    PhotoNotesTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    GenericAppBar(
                        title = note.value.title,
                        onIconClick = {
                            navController.navigate(Constants.noteEditNavigation(note.value.id ?: 0))
                        },
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.edit_note),
                                contentDescription = stringResource(R.string.edit_note),
                                tint = Color.Black,
                            )
                        },
                        iconState = remember { mutableStateOf(true) }
                    )
                },

            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                ) {

                    if (note.value.imageUri != null && note.value.imageUri!!.isNotEmpty()) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest
                                    .Builder(LocalContext.current)
                                    .data(data = Uri.parse(note.value.imageUri))
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
                    Text(
                        text = note.value.title,
                        modifier = Modifier.padding(top = 24.dp, start = 12.dp, end = 24.dp),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = note.value.dateUpdated, Modifier.padding(12.dp), color = Color.Gray)
                    Text(text = note.value.note, Modifier.padding(12.dp))
                }

            }
        }
    }
}