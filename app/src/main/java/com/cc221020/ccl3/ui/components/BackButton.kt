package com.cc221020.ccl3.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BackButton(navController: NavController){

    var page = navController.currentBackStackEntryAsState().value?.destination?.route

    Button(onClick = {
        if(page != "goalView/{goalId}"){
            navController.navigate("avatar")
        }
        else{
            navController.navigate("you")
        }
    }) {
        Text(text = "Back to Avatar")
    }
}