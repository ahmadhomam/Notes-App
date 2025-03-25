package com.example.notesdrawer.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesdrawer.Models.Notes
import com.example.notesdrawer.ui.theme.black
import com.example.notesdrawer.ui.theme.dark_Grey

@Preview
@Composable
fun NotesScreen(){

    val noteslist = listOf(
        Notes(
            title = "Meeting with team ",
            description = "Discuss project roadmap and next project "
        ),
        Notes(
            title = "Meeting with team ",
            description = "Discuss project roadmap and next project "
        ),
        Notes(
            title = "Meeting with team ",
            description = "Discuss project roadmap and next project "
        ),
        Notes(
            title = "Meeting with team ",
            description = "Discuss project roadmap and next project "
        ),
        Notes(
            title = "Meeting with team ",
            description = "Discuss project roadmap and next project "
        ),
        Notes(
            title = "Meeting with team ",
            description = "Discuss project roadmap and next project "
        ),
        Notes(
            title = "Meeting with team ",
            description = "Discuss project roadmap and next project "
        ),
        Notes(
            title = "Meeting with team ",
            description = "Discuss project roadmap and next project "
        )



    )

    Scaffold (floatingActionButton = {
        FloatingActionButton(containerColor = Color.Red,contentColor = Color.White,onClick = {}) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }){ innerpadding ->
        Box(modifier = Modifier
            .padding(innerpadding)
            .background(black)
            .fillMaxSize()){
            Column (modifier = Modifier
                .padding(15.dp)){
                Text(text = "Create Notes\nCrude",
                    style = TextStyle(fontSize = 32.sp, color = Color.White, fontWeight = FontWeight.Bold))
                LazyColumn {
                    items(noteslist){notes->
                        Listitems(notes)
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun ListingItemspreview(){
    Listitems(notes = Notes("hey this is a title",
        "hello how are you"))
}

@Composable
fun Listitems(notes: Notes){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp)))
        .background(color = dark_Grey)
        ){
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "", modifier =
        Modifier.align(Alignment.TopEnd)
            .padding(10.dp), tint = Color.White)

        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = notes.title, style = TextStyle(color  = Color.White, fontSize = 20.sp))
            Text(text = notes.description, style = TextStyle(color = Color.White, fontSize = 19.sp))
        }
    }
}