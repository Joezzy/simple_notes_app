package com.test.simple_note.presentation.note_list.note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen (
    state: NoteState,
    onEvent: (NoteEvent) -> Unit
){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(onClick={
                        onEvent(NoteEvent.NavigateBack)
                    }){
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        onEvent(NoteEvent.DeleteNote)
                    }) {

                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = null)
                    }
                }

                )
        }
    ) {padding->
        Column(
            modifier= Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {

            OutlinedTextField(
                modifier=Modifier.fillMaxWidth(),
                value = state.title,
                placeholder = {
                              Text("Title")
                },
                onValueChange ={
                    onEvent(NoteEvent.TitleChange(it))
                } )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                modifier=Modifier.fillMaxWidth(),
                value = state.content,
                placeholder = {
                    Text("Content")
                },
                onValueChange ={
                    onEvent(NoteEvent.ContentChange(it))
                } )
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier=Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center ){
                Button(
                    onClick = {
                     onEvent(NoteEvent.Save)
                },
                    modifier=Modifier.fillMaxWidth(1f)
                        .height(50.dp)
                    ) {
                    Text(text="Save")
                }
            }



        }


    }
}