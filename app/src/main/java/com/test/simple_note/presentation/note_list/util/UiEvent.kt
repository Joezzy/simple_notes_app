package com.test.simple_note.presentation.note_list.util

sealed interface UiEvent {
data class Navigate(val route:String): UiEvent
    object  NavigateBack:UiEvent
}