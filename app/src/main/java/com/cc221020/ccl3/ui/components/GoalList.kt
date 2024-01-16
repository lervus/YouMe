package com.cc221020.ccl3.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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

@Composable
fun GoalList(navController: NavController ,mainViewModel: MainViewModel){

    mainViewModel.getGoals();
    val state = mainViewModel.mainViewState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .padding(top = 50.dp)
    ){

        item{ Text(text = "Your Goals:" ) }
        items(state.value.goals){
            Column {
                Text(text = it.title)
            }
            IconButton(onClick = { mainViewModel.deleteItem(it) }) {
                Icon(Icons.Default.Delete, "Delete", modifier = Modifier.size(35.dp))
            }
        }
    }
}