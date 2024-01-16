package com.cc221020.ccl3.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BackButton(navController: NavController){
    Button(onClick = {navController.navigate("avatar")}) {
        Text(text = "Back to Avatar")
    }
}