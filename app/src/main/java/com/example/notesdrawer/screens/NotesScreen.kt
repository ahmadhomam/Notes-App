package com.example.notesdrawer.screens

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import com.example.notesdrawer.Models.Navigation.NotesNavigation
import com.example.notesdrawer.Models.Navigation.NotesNavigationItem
import com.example.notesdrawer.Models.Notes
import com.example.notesdrawer.ui.theme.black
import com.example.notesdrawer.ui.theme.dark_Grey
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NotesScreen(navHostController: NavHostController){

    val db = FirebaseFirestore.getInstance()
    val notesDBref = db.collection("notes")
    val noteslist = remember { mutableStateListOf<Notes>()  }
    val loading = remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        notesDBref.addSnapshotListener { value, error ->
            if(error ==  null){
                val data = value?.toObjects(Notes::class.java)
                noteslist.clear()
                noteslist.addAll(data!!)
                loading.value = true
            }
            else{
                loading.value  = false
            }
        }
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(containerColor = Color.Red, contentColor = Color.White, onClick = {

            navHostController.navigate(NotesNavigationItem.insertNotesScreen.route)
        })
        { Icon(imageVector = Icons.Default.Add, contentDescription = "") }
    }){ innerpadding ->
        Box(modifier = Modifier
            .padding(innerpadding)
            .background(black)
            .fillMaxSize()){
            Column (modifier = Modifier
                .padding(15.dp)){
                Text(text = "Create Notes\nCrude",
                    style = TextStyle(fontSize = 32.sp, color = Color.White, fontWeight = FontWeight.Bold))

                if (loading.value) {
                    LazyColumn {
                        items(noteslist) { notes ->
                            Listitems(notes,notesDBref)
                        }
                    }
                }
                else{
                    Box(modifier = Modifier.fillMaxSize()){
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(25.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun Listitems(notes: Notes, notesDBref: CollectionReference){

    val context = LocalContext.current

    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp)))
        .background(color = dark_Grey)
        ){

        DropdownMenu(modifier = Modifier.background(color = Color.White),
            properties = PopupProperties(clippingEnabled = true),
            offset = DpOffset(x  = (-40).dp, y= 0.dp),
            expanded = expanded, onDismissRequest = {}) {
            DropdownMenuItem(text = {Text(text = "Update", style = TextStyle(color = Color.Black))}, onClick = {})
            DropdownMenuItem(text = {Text(text = "Delete", style = TextStyle(color = Color.Black))},
                onClick = {

                    val alertDialog = AlertDialog.Builder( context)
                    alertDialog.setMessage("Are you sure \nWant to delete this notes ?")
                    alertDialog.setPositiveButton("yes"
                    ) { dialog, which ->
                        notesDBref.document(notes.id).delete()
                        dialog?.dismiss()
                    }
                    alertDialog.setNegativeButton("No"
                    ) { dialog, which -> dialog?.dismiss() }

                    alertDialog.show()
                })

        }
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "", modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(10.dp)
            .clickable { expanded = true }, tint = Color.White)

        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = notes.title, style = TextStyle(color  = Color.White, fontSize = 20.sp))
            Text(text = notes.description, style = TextStyle(color = Color.White, fontSize = 19.sp))
        }
    }
}