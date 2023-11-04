package com.test.simple_note.presentation.note_list.note

sealed interface NoteEvent{
    data class TitleChange(val value: String):NoteEvent
    data class ContentChange(val value: String):NoteEvent
    object Save :NoteEvent
    object NavigateBack :NoteEvent
    object DeleteNote :NoteEvent
}