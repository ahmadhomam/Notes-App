package com.example.notesdrawer.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.notesdrawer.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.notesdrawer.Models.Navigation.NotesNavigationItem
import kotlinx.coroutines.delay

@Composable
fun splashscreen(navHostController: NavHostController) {
    Scaffold { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(color = Color.Black)){
            Image(painterResource(id = R.drawable.logo), contentDescription = "",modifier =
            Modifier.size(150.dp)
                .align(Alignment.Center))

        }
    }
    LaunchedEffect(Unit) {
        delay(1300)
        navHostController.navigate(NotesNavigationItem.NotesScreen.route){
            popUpTo(NotesNavigationItem.SplashScreen.route){
                inclusive = true
            }
        }
    }
}
