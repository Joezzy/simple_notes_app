package com.test.simple_note.di

import android.content.Context
import androidx.room.Room
import com.test.simple_note.data.local.NoteDatabase
import com.test.simple_note.data.local.repository.NotesRepositoryImpl
import com.test.simple_note.domain.model.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            NoteDatabase.db_name
        ).build()

    @Provides
    @Singleton
    fun provideNoteRepository(database: NoteDatabase):NoteRepository=
        NotesRepositoryImpl(dao=database.dao)
}