package com.test.simple_note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.simple_note.presentation.nav.NavGraph
import com.test.simple_note.presentation.note_list.NoteListView
import com.test.simple_note.presentation.note_list.NoteListViewModel
import com.test.simple_note.presentation.note_list.note.NoteScreen
import com.test.simple_note.presentation.note_list.note.NoteViewModel
import com.test.simple_note.presentation.nav.Route
import com.test.simple_note.presentation.note_list.util.UiEvent
import com.test.simple_note.ui.theme.Simple_noteTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Simple_noteTheme {
                NavGraph(startDestination = Route.noteList)



//                    val navController= rememberNavController()
//                NavHost(
//                    navController = navController,
//                    startDestination = Route.noteList){
//                    composable(route= Route.noteList){
//                        val viewModel= hiltViewModel<NoteListViewModel>()
//                         val noteList by viewModel.noteList.collectAsState()
//                        NoteListView(noteList = noteList,
//                            onNoteClick ={note->
//                            navController.navigate(
//                                Route.note.replace("{id}",note.id.toString())
//                            )
//                        },
//                            onAddNoteClick = {
//                                navController.navigate(Route.note)
//                            }
//
//                            )
//                    }
//
//
//                    composable(route= Route.note){
//                        val viewModel= hiltViewModel<NoteViewModel>()
//                        val state by viewModel.state.collectAsState()
//
//                        LaunchedEffect(key1 = true ){
//                            viewModel.event.collect{event ->
//                                when (event){
//                                    is UiEvent.NavigateBack->{
//                                        navController.popBackStack()
//                                    }
//                                    else -> Unit
//                                }
//                            }
//                        }
//
//
//                        NoteScreen(state = state, onEvent =viewModel::onEvent )
//                    }
//                }



            }
        }
    }
}

