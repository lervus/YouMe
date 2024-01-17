package com.cc221020.ccl3.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.cc221020.ccl3.MainViewModel
import com.cc221020.ccl3.R
import com.cc221020.ccl3.data.Goal

@Composable
fun Avatar(navController: NavController, mainViewModel: MainViewModel){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "This is the Avatar Page")
        Image(
            modifier = Modifier,
            painter = painterResource(id = R.drawable.ic_action_name),
            contentDescription = stringResource(id = R.string.avatar_image)
        )
        Row {
            Button(onClick = { navController.navigate("me") }) {
                Text(text = "Me")
            }
            Button(onClick = { navController.navigate("you") }) {
                Text(text = "You")
            }
        }
        Button(onClick = { mainViewModel.saveGoal(Goal(title = "yeyeyeyeyyeyeyeyeyyeyeyeyey", completed = false)) }) {
            Text(text = "Test")
        }
    }
}