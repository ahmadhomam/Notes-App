package com.example.notesdrawer.Models.Navigation

sealed class NotesNavigationItem (val route: String){
    object SplashScreen : NotesNavigationItem(route = "splash")
    object NotesScreen : NotesNavigationItem(route = "home")
    object insertNotesScreen : NotesNavigationItem(route = "insert")
}
