package com.cc221020.ccl3.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(){

    val navController = rememberNavController()
    Scaffold(){
        NavHost(
            navController = navController,
            modifier = Modifier.padding(it),
            startDestination = "enter"
        ){
            composable("me"){ MeView(navController) }
            composable("you"){ YouView(navController) }
            composable( "avatar"){ Avatar(navController) }
            composable( "enter"){ Enter(navController) }
        }
    }
}