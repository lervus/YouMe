package com.cc221020.ccl3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221020.ccl3.MainViewModel
import com.cc221020.ccl3.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeView(navController: NavController, mainViewModel: MainViewModel) {
    var selectedFoodButton by remember { mutableStateOf(0) }
    var selectedDrinkButton by remember { mutableStateOf(0) }

    val state = mainViewModel.mainViewState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "ME",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Go Back",
                        modifier = Modifier
                            .clickable {
                                navController.navigate("avatar")
                            }
                            .size(50.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                navController.navigate("settings")
                            }
                            .size(50.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Challenge of the day:")
                Text(
                    text = stringResource(id = state.value.userInfo.currentDaily),
                    style = MaterialTheme.typography.titleSmall,
                )
                IconButton(onClick = {
                    if(!state.value.userInfo.dailyComplete){
                        mainViewModel.userAddXp(10)
                        mainViewModel.updateUser(state.value.userInfo.copy(dailyComplete = true))
                    }
                }
                ) {
                    Icon(
                        Icons.Default.CheckCircle,
                        "Complete",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                BoxWithRadioButtonsFood(
                    mainViewModel = mainViewModel,
                    modifier = Modifier
                        .height(200.dp)
                        .width(300.dp)
                        .padding(16.dp)
                        .shadow(elevation = 10.dp, shape = MaterialTheme.shapes.medium),
                    boxColor = MaterialTheme.colorScheme.secondary,
                    buttonText = "DIS FOOD",
                    selectedRadioIndex = selectedFoodButton
                ) { selectedFoodButton = it }
                Spacer(modifier = Modifier.height(1.dp))
                BoxWithRadioButtonsDrink(
                    mainViewModel = mainViewModel,
                    modifier = Modifier
                        .height(200.dp)
                        .width(300.dp)
                        .padding(16.dp)
                        .shadow(elevation = 10.dp, shape = MaterialTheme.shapes.medium),
                    boxColor = MaterialTheme.colorScheme.secondary,
                    buttonText = "DIS DRANK",
                    selectedRadioIndex = selectedDrinkButton
                ) { selectedDrinkButton = it }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    )
}

@Composable
fun BoxWithRadioButtonsFood(
    modifier: Modifier,
    boxColor: Color,
    buttonText: String,
    selectedRadioIndex: Int,
    mainViewModel: MainViewModel,
    onSelected: (Int) -> Unit
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(boxColor)
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = buttonText)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                (0..2).forEach { index ->
                    RadioButton(
                        selected = selectedRadioIndex == index,
                        onClick = {
                            onSelected(index)
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (selectedRadioIndex == 0) {
                        mainViewModel.userAddXp(10)
                    } else if (selectedRadioIndex == 1) {
                        mainViewModel.userAddXp(20)
                    } else if (selectedRadioIndex == 2) {
                        mainViewModel.userAddXp(30)
                    }
                },
                modifier = Modifier
                    .padding(1.dp)
                    .size(70.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp,
                    pressedElevation = 1.dp
                ),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun BoxWithRadioButtonsDrink(
    modifier: Modifier,
    boxColor: Color,
    buttonText: String,
    selectedRadioIndex: Int,
    mainViewModel: MainViewModel,
    onSelected: (Int) -> Unit
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(boxColor)
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = buttonText)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                (0..3).forEach { index ->
                    RadioButton(
                        selected = selectedRadioIndex == index,
                        onClick = {
                            onSelected(index)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (selectedRadioIndex == 0) {
                        mainViewModel.userAddXp(10)
                    } else if (selectedRadioIndex == 1) {
                        mainViewModel.userAddXp(20)
                    } else if (selectedRadioIndex == 2) {
                        mainViewModel.userAddXp(30)
                    } else if (selectedRadioIndex == 3) {
                        mainViewModel.userAddXp(40)
                    }
                },
                modifier = Modifier
                    .padding(1.dp)
                    .size(70.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp,
                    pressedElevation = 1.dp
                ),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    modifier = Modifier
                )
            }
        }
    }
}