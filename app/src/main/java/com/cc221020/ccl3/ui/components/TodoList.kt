package com.cc221020.ccl3.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221020.ccl3.MainViewModel
import com.cc221020.ccl3.view.GoalView

@Composable
fun TodoList(navController: NavController ,mainViewModel: MainViewModel, goalId: Int){

    mainViewModel.getTodos(goalId);
    val state = mainViewModel.mainViewState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(25.dp)
    ){

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
                    Row {
                        Text(
                            text = it.title,
                            modifier = Modifier
                                .width(200.dp)
                                .padding(16.dp)
                        )
                        IconButton(onClick = { mainViewModel.completeTodo(it)}) {
                            Icon(Icons.Default.CheckCircle, "Complete")
                        }
                        if(it.completed){
                            Icon(imageVector = Icons.Default.Done, contentDescription =" Done" )
                        }
                        IconButton(
                            modifier = Modifier, onClick = { mainViewModel.deleteTodo(it) }) {
                            Icon(Icons.Default.Delete, "Delete", modifier = Modifier.size(35.dp))
                        }
                    }
                }
            }
        }
    }
