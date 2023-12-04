package com.example.note.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.note.presentation.homePage.home

@Composable
fun navigation() {
    var nav = rememberNavController()
    NavHost(navController = nav, startDestination = "home"){
        composable("home"){
            home(nav)
        }
        composable("information"){
            information(nav)
        }
        composable("add_note"){
            add_note(nav)
        }
    }
}