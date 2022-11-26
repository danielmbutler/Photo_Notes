package com.dbtechprojects.photonotes

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dbtechprojects.photonotes.ui.EditNote.NoteEditScreen
import com.dbtechprojects.photonotes.ui.NoteDetail.NoteDetailScreen
import com.dbtechprojects.photonotes.ui.NotesList.NotesList
import com.dbtechprojects.photonotes.ui.NotesViewModel
import com.dbtechprojects.photonotes.ui.NotesViewModelFactory
import com.dbtechprojects.photonotes.ui.createNote.CreateNoteScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainActivity : ComponentActivity() {

    private lateinit var notesViewModel : NotesViewModel

    //create datastore
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // retrieve viewModel
        notesViewModel =  NotesViewModelFactory(PhotoNotesApp.getDao(), dataStore).create(NotesViewModel::class.java)


        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Constants.NAVIGATION_NOTES_LIST
            ) {
                // Notes List
                composable(Constants.NAVIGATION_NOTES_LIST) { NotesList(navController, notesViewModel) }

                // Notes Detail page
                composable(
                    Constants.NAVIGATION_NOTE_DETAIL,
                    arguments = listOf(navArgument(Constants.NAVIGATION_NOTE_ID_Argument) {
                        type = NavType.IntType
                    })
                ) { backStackEntry ->
                    backStackEntry.arguments?.getInt(Constants.NAVIGATION_NOTE_ID_Argument)
                        ?.let { NoteDetailScreen(noteId = it, navController, notesViewModel) }
                }

                // Notes Edit page
                composable(
                    Constants.NAVIGATION_NOTE_EDIT,
                    arguments = listOf(navArgument(Constants.NAVIGATION_NOTE_ID_Argument) {
                        type = NavType.IntType
                    })
                ) { backStackEntry ->
                    backStackEntry.arguments?.getInt(Constants.NAVIGATION_NOTE_ID_Argument)
                        ?.let { NoteEditScreen(noteId = it, navController, notesViewModel) }
                }

                // Create Note Page
                composable(Constants.NAVIGATION_NOTES_CREATE) { CreateNoteScreen(navController, notesViewModel) }

            }

        }
    }
}
