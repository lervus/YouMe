package com.cc221020.ccl3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221020.ccl3.MainViewModel


@Composable
fun TodoList(navController: NavController, mainViewModel: MainViewModel, goalId: Int) {

    mainViewModel.getTodos(goalId)
    val state = mainViewModel.mainViewState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(15.dp)
    ) {

        item {
            Text(
                text = "Your To-Do´s:",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
        items(state.value.todos) {

            Column {
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(1.dp)
                )
                Row {
                    Text(
                        text = it.title,
                        modifier = Modifier
                            .width(150.dp)
                            .padding(10.dp)
                    )
                    IconButton(onClick = { mainViewModel.completeTodo(it) }) {
                        Icon(Icons.Default.CheckCircle, "Complete")
                    }
                    if (it.completed) {
                        AlertDialog(
                            modifier = Modifier
                                .fillMaxWidth(),
                            onDismissRequest = {
                                mainViewModel.closeAddWindow()
                            },
                            title = {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Is this To-Do done?",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = Color.Black
                                    )
                                }
                            },
                            text = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Button(
                                        onClick = {
                                            mainViewModel.completeTodo(it)
                                        },
                                    ) {
                                        Text("NO")
                                    }
                                    Spacer(modifier = Modifier.width(32.dp))
                                    Button(onClick = {
                                        it.completed = false
                                        mainViewModel.editTodo(it)
                                    }) {
                                        Text("YES")
                                    }
                                }
                            },
                            dismissButton = {

                            },
                            confirmButton = {

                            }
                        )
                    }
                }
            }
        }
    }
}
