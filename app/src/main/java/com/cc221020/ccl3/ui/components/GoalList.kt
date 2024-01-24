package com.cc221020.ccl3.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221020.ccl3.MainViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun GoalList(navController: NavController, mainViewModel: MainViewModel) {

    mainViewModel.getGoals()
    val state = mainViewModel.mainViewState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(15.dp)
    ) {

        stickyHeader {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.tertiary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Your Goals:",
                    modifier = Modifier
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    color = MaterialTheme.colorScheme.onTertiary,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onTertiary)
                    .padding(1.dp)
            )
        }

        items(state.value.goals) {

            Column {
                Row {
                    Text(
                        text = it.title,
                        modifier = Modifier
                            .width(150.dp)
                            .padding(10.dp)
                            .clickable(onClick = { navController.navigate("GoalView/${it.id}") }),
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                    IconButton(
                        onClick = { mainViewModel.completeGoal(it) },
                        modifier = Modifier
                    ) {
                        Icon(
                            Icons.Default.CheckCircle,
                            "Complete",
                            tint = MaterialTheme.colorScheme.onTertiary
                        )
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
                                    val backgroundColor = MaterialTheme.colorScheme.background
                                    val textColor = if (backgroundColor.luminance() > 0.5) {
                                        MaterialTheme.colorScheme.onTertiary
                                    } else {
                                        Color.White
                                    }
                                    Text(
                                        text = "Is this goal done?",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = textColor
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
                                            mainViewModel.completeGoal(it)
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.primary,
                                            contentColor = MaterialTheme.colorScheme.onTertiary
                                        )
                                    ) {
                                        Text("NO")
                                    }
                                    Spacer(modifier = Modifier.width(32.dp))
                                    Button(
                                        onClick = {
                                            it.completed = false
                                            mainViewModel.editGoal(it)
                                            /*mainViewModel.userAddXp(10)
                                            mainViewModel.updateUser(state.value.userInfo.copy(goalsCompleted = state.value.userInfo.goalsCompleted + 1))*/
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.primary,
                                            contentColor = MaterialTheme.colorScheme.onTertiary
                                        )
                                    ) {
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
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.onTertiary)
                        .padding(1.dp)
                )
            }
        }
    }
}
