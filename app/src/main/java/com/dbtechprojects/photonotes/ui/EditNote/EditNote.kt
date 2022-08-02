package com.dbtechprojects.photonotes.ui.EditNote

@Composable
fun NoteEditScreen(noteId: Int){
    val note = TestConstants.notes.find{note -> note.id == noteId  } ?: Note(note = "Cannot find note details", id = 0, title = "Cannot find note details")
    PhotoNotesTheme{
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    NoteDetailAppBar(
                        title = stringResource("Edit Note"),
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