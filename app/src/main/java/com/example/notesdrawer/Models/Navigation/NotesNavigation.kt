package com.example.notesdrawer.Models.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notesdrawer.screens.NotesScreen
import com.example.notesdrawer.screens.insertNotesScreen
import com.example.notesdrawer.screens.splashscreen

@Composable
fun NotesNavigation(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = "splash" )
    {
        composable(NotesNavigationItem.SplashScreen.route) {
            splashscreen(navHostController)
        }
        composable(NotesNavigationItem.NotesScreen.route){
            NotesScreen()
        }
        composable(NotesNavigationItem.insertNotesScreen.route){
            insertNotesScreen()
        }
    }
}