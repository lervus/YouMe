package com.cc221020.ccl3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.TextStyle
import com.cc221020.ccl3.MainViewModel
import com.cc221020.ccl3.data.Goal
import com.cc221020.ccl3.data.TodoItem
import com.cc221020.ccl3.data.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWindow(mainViewModel: MainViewModel, goalId: Int?) {

    val state = mainViewModel.mainViewState.collectAsState()
    var title: String by rememberSaveable { mutableStateOf(" ") }
    title = ""

    if (state.value.addGoal) {

        AlertDialog(
            onDismissRequest = {
                mainViewModel.closeAddWindow()
            },
            text = {

                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                )
                {
                    val backgroundColor = MaterialTheme.colorScheme.background
                    val textColor = if (backgroundColor.luminance() > 0.5) {
                        MaterialTheme.colorScheme.onTertiary
                    } else {
                        Color.White
                    }
                    Column {
                        Text(text = "Title:")
                        TextField(
                            value = title,
                            onValueChange = { newText -> title = newText },
                            textStyle = TextStyle(color = textColor),
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (goalId != null) {
                            mainViewModel.saveTodo(
                                TodoItem(
                                    title = title,
                                    completed = false,
                                    goalId = goalId
                                )
                            )
                        } else {
                            mainViewModel.saveGoal(Goal(title = title, completed = false))
                        }
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                ) {
                    Text("Save")
                }
            }
        )
    }
}