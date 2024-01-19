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
fun GoalList(navController: NavController ,mainViewModel: MainViewModel){

    mainViewModel.getGoals();
    val state = mainViewModel.mainViewState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .padding(top = 25.dp)
    ){

        item{ Text(text = "Your Goals:" ) }
        items(state.value.goals){
            Row {
                Text(text = it.title, modifier = Modifier.clickable(onClick = {navController.navigate("GoalView/${it.id}")}))
                IconButton(onClick = { mainViewModel.deleteGoal(it) }) {
                    Icon(Icons.Default.Delete, "Delete", modifier = Modifier.size(35.dp))
                }
                IconButton(onClick = { mainViewModel.completeGoal(it)}) {
                    Icon(Icons.Default.CheckCircle, "Complete")
                }
                if(it.completed){
                    Icon(imageVector = Icons.Default.Done, contentDescription =" Done" )
                }
            Column {
                Row {
                    Text(
                        text = it.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .width(200.dp)
                            .padding(16.dp)
                            .clickable(onClick = { navController.navigate("GoalView/${it.id}") })
                    )
                    IconButton(
                        modifier = Modifier
                        ,onClick = { mainViewModel.deleteGoal(it) }) {
                        Icon(Icons.Default.Delete, "Delete", modifier = Modifier.size(35.dp))
                    }
                }
            }
        }
    }
}}