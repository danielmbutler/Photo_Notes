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
import com.dbtechprojects.photonotes.NotesViewModel
import com.dbtechprojects.photonotes.ui.GenericAppBar
import com.dbtechprojects.photonotes.ui.theme.PhotoNotesTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteDetailPage(noteId: String, navController: NavController, viewModel: NotesViewModel) {

    val note = remember {
        mutableStateOf(noteDetailPlaceHolder)
    }

    LaunchedEffect(true) {

        note.value = viewModel.getNote(noteId) ?: noteDetailPlaceHolder

    }

    PhotoNotesTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    GenericAppBar(
                        title = note.value.title,
                        onIconClick = {
                            navController.navigate(
                                Constants.noteEditNavigation(
                                    note.value.id.toString()
                                )
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = com.dbtechprojects.photonotes.R.drawable.edit_note),
                                contentDescription = stringResource(id = com.dbtechprojects.photonotes.R.string.edit_note),
                                tint = Color.Black
                            )
                        },
                        iconState = remember {
                            mutableStateOf(true)
                        }
                    )
                }
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
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
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Text(
                        text = note.value.title,
                        modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = note.value.dateUpdated,
                        modifier = Modifier.padding(12.dp),
                        color = Color.Gray
                    )

                    Text(
                        text = note.value.note,
                        modifier = Modifier.padding(12.dp),
                    )
                }
            }
        }
    }


}