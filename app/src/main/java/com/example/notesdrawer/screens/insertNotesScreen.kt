package com.example.notesdrawer.screens

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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesdrawer.ui.theme.PurpleGrey40
import com.example.notesdrawer.ui.theme.black
import com.example.notesdrawer.ui.theme.dark_Grey

@Composable
@Preview
fun insertNotesScreen(){
    Scaffold (floatingActionButton = {
        FloatingActionButton(containerColor = Color.Red, contentColor = Color.White,onClick = {}) {
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
                TextField(colors = TextFieldDefaults.colors(
                    focusedContainerColor = dark_Grey,
                    unfocusedContainerColor = dark_Grey,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,),
                    shape = RoundedCornerShape(corner = CornerSize(12.dp)),
                    label = { Text(text = "Enter the Title",
                        style = TextStyle(fontSize = 18.sp), color = Color.Gray) },
                    value = "", onValueChange = {},
                    modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(15.dp))
                TextField(colors = TextFieldDefaults.colors(
                    focusedContainerColor = dark_Grey,
                    unfocusedContainerColor = dark_Grey,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,),
                    shape = RoundedCornerShape(corner = CornerSize(12.dp)),
                    label = { Text(text = "Enter the Title",
                        style = TextStyle(fontSize = 18.sp), color = Color.Gray) },
                    value = "", onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f))
            }
        }
    }
}