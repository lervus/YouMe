package com.cc221020.ccl3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cc221020.ccl3.MainViewModel
import com.cc221020.ccl3.data.Goal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWindow(mainViewModel: MainViewModel){

    val state = mainViewModel.mainViewState.collectAsState()
    var title: String by rememberSaveable { mutableStateOf(" ") }

    if(state.value.addGoal){

        AlertDialog(
            onDismissRequest = {
                mainViewModel.closeAddWindow()
            },
            text = {

        Box(modifier = Modifier.background(Color.White))
        {
            Column {
                Text(text = "Title:")
                TextField(value = title, onValueChange = {newText -> title = newText})
            }
        }
            },
            confirmButton = {
                Button(onClick = {
                    if (goalId != null) {
                        mainViewModel.saveTodo(TodoItem(title = title, completed = false, goalId = goalId))
                    } else {
                        mainViewModel.saveGoal(Goal(title = title, completed = false))
                    }
                }) {
                    Text("Save")
                }
            }
        )
}
}