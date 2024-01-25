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
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221020.ccl3.MainViewModel
import com.cc221020.ccl3.ui.components.DailyChallenge

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
                DailyChallenge(mainViewModel)
                BoxWithRadioButtonsFood(
                    mainViewModel = mainViewModel,
                    modifier = Modifier
                        .width(300.dp)
                        .padding(16.dp)
                        .shadow(elevation = 10.dp, shape = MaterialTheme.shapes.medium),
                    boxColor = MaterialTheme.colorScheme.secondary,
                    buttonText = "Food",
                    selectedRadioIndex = selectedFoodButton
                ) { selectedFoodButton = it }
                Spacer(modifier = Modifier.height(1.dp))
                BoxWithRadioButtonsDrink(
                    mainViewModel = mainViewModel,
                    modifier = Modifier
                        .width(300.dp)
                        .padding(16.dp)
                        .shadow(elevation = 10.dp, shape = MaterialTheme.shapes.medium),
                    boxColor = MaterialTheme.colorScheme.secondary,
                    buttonText = "Drink",
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "What did you eat?", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            (0..2).forEach { index ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    RadioButton(
                        selected = selectedRadioIndex == index,
                        onClick = { onSelected(index) }
                    )
                    Text(
                        text = getFoodTitle(index)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    mainViewModel.userAddXp(getXpForFood(selectedRadioIndex))
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "What did you drink?",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))

           Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                (0..3).forEach { index ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = getDrinkTitle(index)
                        )
                        RadioButton(
                            selected = selectedRadioIndex == index,
                            onClick = { onSelected(index) }
                        )
                    }
               }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    mainViewModel.userAddXp(getXpForDrink(selectedRadioIndex))
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


fun getFoodTitle(index: Int): String {
    return when (index) {
        0 -> "Fast Food"
        1 -> "Eating out"
        2 -> "Home cooked"
        else -> ""
    }
}

fun getDrinkTitle(index: Int): String {
    return when (index) {
        0 -> "1/4 l"
        1 -> "1/2 l"
        2 -> "3/4 l"
        3 -> "1 l"
        else -> ""
    }
}

fun getXpForFood(index: Int): Int {
    return when (index) {
        0 -> 10
        1 -> 20
        2 -> 30
        else -> 0
    }
}

fun getXpForDrink(index: Int): Int {
    return when (index) {
        0 -> 20
        1 -> 30
        2 -> 40
        3 -> 50
        else -> 0
    }
}
