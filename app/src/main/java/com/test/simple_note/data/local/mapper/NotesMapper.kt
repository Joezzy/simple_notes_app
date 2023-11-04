package com.test.simple_note.data.local.mapper

import com.test.simple_note.data.local.entity.NoteEntity
import com.test.simple_note.domain.model.Note


fun NoteEntity.asExternalModel(): Note= Note(
    id , title, content
)

fun Note.toEntity(): NoteEntity = NoteEntity(
    id, title, content
)