package com.dbtechprojects.photonotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dbtechprojects.photonotes.ui.NoteCreate.CreateNoteScreen
import com.dbtechprojects.photonotes.ui.NoteDetail.NoteDetailPage
import com.dbtechprojects.photonotes.ui.NoteEdit.NoteEditScreen
import com.dbtechprojects.photonotes.ui.NoteList.NoteListScreen
import com.dbtechprojects.photonotes.ui.NoteList.NotesList

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = NoteViewModelFactory(PhotoNotesApp.getDao()).create(NotesViewModel::class.java)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Constants.NAVIGATION_NOTES_LIST){
                // Notes List
                composable(Constants.NAVIGATION_NOTES_LIST){ NoteListScreen(
                    navController = navController,
                    viewModel = viewModel
                )}

                // note detail page
                composable(
                    Constants.NAVIGATION_NOTES_DETAIL,
                    arguments = listOf(navArgument(Constants.NAVIGATION_NOTES_ID_ARGUMENT){
                        type = NavType.StringType
                    })
                ){ navBackStackEntry ->
                    navBackStackEntry.arguments?.getString(Constants.NAVIGATION_NOTES_ID_ARGUMENT)?.let {
                        NoteDetailPage(noteId = it, navController = navController, viewModel = viewModel)
                    }
                }

                // note edit page
                composable(
                    Constants.NAVIGATION_NOTES_EDIT,
                    arguments = listOf(navArgument(Constants.NAVIGATION_NOTES_ID_ARGUMENT){
                        type = NavType.StringType
                    })
                ){ navBackStackEntry ->
                    navBackStackEntry.arguments?.getString(Constants.NAVIGATION_NOTES_ID_ARGUMENT)?.let {
                        NoteEditScreen(noteId = it, navController = navController, viewModel = viewModel)
                    }
                }

                // note create page
                composable(Constants.NAVIGATION_NOTES_CREATE){
                    CreateNoteScreen(navController = navController, viewModel = viewModel )
                }
            }
        }












    }
}