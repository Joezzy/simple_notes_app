package com.test.simple_note.data.local.repository

import com.test.simple_note.data.local.dao.NoteDao
import com.test.simple_note.data.local.mapper.asExternalModel
import com.test.simple_note.data.local.mapper.toEntity
import com.test.simple_note.domain.model.Note
import com.test.simple_note.domain.model.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl(
    private  val dao:NoteDao
): NoteRepository{

    override fun getAllNotes(): Flow<List<Note>> {
        return  dao.getAllNotes()
            .map { notes->
                notes.map {
                    it.asExternalModel()
                }
            }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)?.asExternalModel()
    }



    override suspend fun insertNote(note: Note) {
        dao.insertNote(note.toEntity())
    }


    override suspend fun updateNote(note: Note) {
         dao.updateNote(note.toEntity())
    }


    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.toEntity())
    }
}