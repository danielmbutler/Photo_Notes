package com.dbtechprojects.photonotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dbtechprojects.photonotes.test.TestConstants
import com.dbtechprojects.photonotes.ui.NoteDetail.NoteDetailScreen
import com.dbtechprojects.photonotes.ui.NotesList.NotesList

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = PhotoNotesApp.getDao()


        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Constants.NAVIGATION_NOTES_LIST) {
                composable(Constants.NAVIGATION_NOTES_LIST) { NotesList(navController) }
                composable(
                    Constants.NAVIGATION_NOTE_DETAIL,
                    arguments = listOf(navArgument(Constants.NAVIGATION_NOTE_DETAIL_Argument) { type = NavType.IntType })
                ) { backStackEntry ->
                    backStackEntry.arguments?.getInt(Constants.NAVIGATION_NOTE_DETAIL_Argument)
                        ?.let { NoteDetailScreen(noteId = it) }
                }
                composable(
                    Constants.NAVIGATION_NOTE_EDIT,
                    arguments = listOf(navArgument(Constants.NAVIGATION_NOTE_EDIT_Argument) { type = NavType.IntType })
                ) { backStackEntry ->
                    backStackEntry.arguments?.getInt(Constants.NAVIGATION_NOTE_EDIT_Argument)
                        ?.let { NoteEditScreen(noteId = it) }
                }

            }

            }
        }
    }
