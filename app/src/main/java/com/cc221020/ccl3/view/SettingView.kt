package com.cc221020.ccl3.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221020.ccl3.MainViewModel
import com.cc221020.ccl3.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingView(navController: NavController, mainViewModel: MainViewModel) {

    val state = mainViewModel.mainViewState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Settings",
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
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .width(300.dp)
                        .padding(16.dp)
                        .shadow(elevation = 10.dp, shape = MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.secondary)
                ){
                        var waterIntakeGoal by remember { mutableStateOf("") }

                        Column {
                            OutlinedTextField(
                                value = waterIntakeGoal,
                                onValueChange = { newValue ->
                                    if (newValue.isEmpty() || newValue.toFloatOrNull() != null) {
                                        waterIntakeGoal = newValue
                                    }
                                },
                                label = { Text("Water Goal in liters") },
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Done,
                                    keyboardType = KeyboardType.Number
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = {
                                    if (waterIntakeGoal.isNotEmpty()) {

                                        val goal = waterIntakeGoal.toFloat()
                                        println("Entered water intake goal: $goal liters")
                                        mainViewModel.updateUser(state.value.userInfo.copy(waterGoal = goal))
                                    } else {
                                        println("Please enter a valid water intake goal")
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Text("Set Water Intake Goal")
                            }
                        }
                }

                Spacer(modifier = Modifier.height(1.dp))

                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .width(300.dp)
                        .padding(16.dp)
                        .shadow(elevation = 10.dp, shape = MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.secondary)
                ){
                    Row(){
                    }
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    )
}

@Composable
fun skinSelect(){

    val predefinedImageIds = listOf(
        R.drawable.ic_action_avatar_1, R.drawable.ic_action_avatar_2, R.drawable.ic_action_avatar_3
    )

    val selectedImageIndex = remember { mutableStateOf(0) }

    /*state.value.userInfo.selectedSkin?.let {
        selectedImageIndex.value = it
    }*/

    Image(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 1000.dp)
            .aspectRatio(1f)
            .scale(0.5f)
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium),
        painter = painterResource(id = predefinedImageIds[selectedImageIndex.value]),
        contentDescription = stringResource(id = R.string.avatar_image)
    )
}