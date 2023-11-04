package com.test.simple_note.presentation.note_list.note

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.simple_note.domain.model.Note
import com.test.simple_note.domain.model.repository.NoteRepository
import com.test.simple_note.presentation.note_list.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private  val repository: NoteRepository,
    private  val savedStateHandle: SavedStateHandle

):ViewModel() {

    // so that the UI will not access the state directly
    private val _state= MutableStateFlow(NoteState())
    val state = _state.asStateFlow()

    private  val _event= Channel<UiEvent>()
    val event =_event.receiveAsFlow()

    private fun sendEvent(event: UiEvent){
        viewModelScope.launch {
            _event.send(event)
        }
    }

    init {
        savedStateHandle.get<String>("id")?.let{
            Log.i("NOTEZ_ID",it.toString())
            val id = it.toInt()
            viewModelScope.launch {
                repository.getNoteById(id)?.let { note->
                    Log.i("NOTEZ",note.title)
                    Log.i("NOTEZ2",note.content)
                _state.update { screenState->
                    screenState.copy(
                        id=note.id,
                        title = note.title,
                        content = note.content
                    )
                }

                }
            }
        }
    }

fun onEvent(event: NoteEvent){
    when(event){
        is NoteEvent.ContentChange ->{
            _state.update {
                it.copy(
                    content = event.value
                )
            }
        }

        is NoteEvent.TitleChange -> {
            _state.update {
                it.copy(
                    title = event.value
                )
            }
        }

        NoteEvent.NavigateBack ->sendEvent(UiEvent.NavigateBack)
            NoteEvent.Save -> {
                 viewModelScope.launch {
                     val state=state.value
                     val note=     Note(
                         id = state.id,
                         title = state.title,
                         content = state.content,
                     )
                     if(state.id==null){
                         repository.insertNote(note)
                     }else{
                         repository.updateNote(note)
                     }
                     sendEvent(UiEvent.NavigateBack)

                 }
            }

        NoteEvent.DeleteNote -> {
            viewModelScope.launch {
                val state=state.value

                val note=     Note(
                    id = state.id,
                    title = state.title,
                    content = state.content,
                )
                repository.deleteNote(note)
            }
            sendEvent(UiEvent.NavigateBack)

        }
    }
}


}