package com.test.simple_note.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.simple_note.data.local.dao.NoteDao
import com.test.simple_note.data.local.entity.NoteEntity

@Database(
    version = 1,
    entities =[NoteEntity::class]
    )
abstract class NoteDatabase: RoomDatabase() {
    abstract  val dao:NoteDao

    companion object{
        const val db_name="notes_db"
    }


}