package com.example.notesdrawer.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.notesdrawer.Models.Notes
import com.example.notesdrawer.ui.theme.black
import com.example.notesdrawer.ui.theme.dark_Grey
import com.google.firebase.firestore.FirebaseFirestore

@Composable

fun insertNotesScreen(navHostController: NavHostController, id: String?) {

    val db = FirebaseFirestore.getInstance()
    val notesDBref = db.collection("notes")

    val context=  LocalContext.current
    val title = remember {
        mutableStateOf("")
    }
    val description = remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        if(id != "default Id"){
            notesDBref.document(id.toString()).get().addOnSuccessListener {
                val singleData = it.toObject(Notes::class.java)
                title.value = singleData!!.title
                description.value = singleData.description
            }
        }
    }

    Scaffold (floatingActionButton = {
        FloatingActionButton(containerColor = Color.Red, contentColor = Color.White,
            onClick = {
                if(title.value.isEmpty()){
                    Toast.makeText(context,"Enter a valid Data",Toast.LENGTH_SHORT).show()
                }
                else{
                    val MyNotesId =  if(id  != "default Id") {
                            id.toString()
                    }else{
                        notesDBref.document().id
                    }
                    val notes = Notes(
                        id = MyNotesId ,
                        title = title.value,
                        description  = description.value,
                    )
                    notesDBref.document(MyNotesId).set(notes).addOnCompleteListener{
                        if(it.isSuccessful){
                            Toast.makeText(context,"Your data is successfully added",Toast.LENGTH_SHORT)
                                .show()
                            navHostController.popBackStack()
                        }
                        else{
                            Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }) {
            Icon(imageVector = Icons.Default.Done, contentDescription = "")
        }
    }) {
        innerpadding->
        Box(modifier = Modifier.padding(innerpadding)
            .fillMaxSize()
            .background(black)){
            Column (modifier = Modifier
                .padding(15.dp)
                ){
                Text(text = "Insert Data", style = TextStyle(color = Color.White,
                    fontSize = 32.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(textStyle = TextStyle(color = Color.White),colors = TextFieldDefaults.colors(
                    focusedContainerColor = dark_Grey,
                    unfocusedContainerColor = dark_Grey,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,),
                    shape = RoundedCornerShape(corner = CornerSize(12.dp)),
                    label = { Text(text = "Enter the Title",
                        style = TextStyle(fontSize = 18.sp), color = Color.Gray) },
                    value = title.value,
                    onValueChange = {title.value =it},
                    modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(15.dp))
                TextField(textStyle = TextStyle(color = Color.White),colors = TextFieldDefaults.colors(
                    focusedContainerColor = dark_Grey,
                    unfocusedContainerColor = dark_Grey,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,),
                    shape = RoundedCornerShape(corner = CornerSize(12.dp)),
                    label = { Text(text = "Enter the Description",
                        style = TextStyle(fontSize = 18.sp), color = Color.Gray) },
                    value = description.value,
                    onValueChange = { description.value= it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f))
            }
        }
    }
}