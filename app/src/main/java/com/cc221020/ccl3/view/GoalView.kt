package com.cc221020.ccl3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221020.ccl3.MainViewModel
import com.cc221020.ccl3.data.Goal
import com.cc221020.ccl3.ui.components.AddWindow
import com.cc221020.ccl3.ui.components.BackButton
import com.cc221020.ccl3.ui.components.TodoList

@Composable
fun GoalView(navController: NavController, mainViewModel: MainViewModel, goal: Goal) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = goal.title,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(15.dp),
            style = MaterialTheme.typography.titleLarge
        )
        Card(
            modifier = Modifier
                .height(500.dp)
                .width(300.dp)
                .padding(16.dp)
                .shadow(elevation = 10.dp, shape = MaterialTheme.shapes.medium),
            shape = MaterialTheme.shapes.medium
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onSecondary)
                    .padding(16.dp),
                contentAlignment = Alignment.TopCenter
            )

            {
                TodoList(
                    navController = navController,
                    mainViewModel = mainViewModel,
                    goalId = goal.id
                )
            }
        }

        Button(
            onClick = { mainViewModel.addGoal() },
            modifier = Modifier
                .padding(16.dp)
                .size(75.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 5.dp,
                pressedElevation = 1.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ),
            shape = CircleShape
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            // Text(text = stringResource(id = R.string.goals_button))
        }
        BackButton(navController = navController)
    }
    AddWindow(mainViewModel = mainViewModel, goalId = goal.id)

}
