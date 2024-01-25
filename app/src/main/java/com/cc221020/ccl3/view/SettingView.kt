package com.cc221020.ccl3.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221020.ccl3.MainViewModel
import com.cc221020.ccl3.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SettingView(navController: NavController, mainViewModel: MainViewModel) {

    val state = mainViewModel.mainViewState.collectAsState()

    val keyboardController = LocalSoftwareKeyboardController.current

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
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "XP: ${state.value.userInfo.xp}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(text = "Personal", style = MaterialTheme.typography.headlineLarge)

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .width(300.dp)
                        .shadow(elevation = 10.dp, shape = MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    var waterIntakeGoal by remember { mutableStateOf("") }

                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = "Daily water goal:",
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                            OutlinedTextField(
                                value = waterIntakeGoal,
                                onValueChange = { newValue ->
                                    if (newValue.isEmpty() || newValue.toFloatOrNull() != null) {
                                        waterIntakeGoal = newValue
                                    }
                                },
                                label = {
                                    Text("Liters", color = MaterialTheme.colorScheme.onTertiary)
                                },
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Done,
                                    keyboardType = KeyboardType.Number
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = MaterialTheme.colorScheme.onTertiary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.onTertiary,
                                    cursorColor = MaterialTheme.colorScheme.onTertiary,
                                    textColor = MaterialTheme.colorScheme.onTertiary
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                keyboardController?.hide()
                                if (waterIntakeGoal.isNotEmpty()) {

                                    val goal = waterIntakeGoal.toFloat()
                                    println("Entered water intake goal: $goal liters")
                                    mainViewModel.updateUser(state.value.userInfo.copy(waterGoal = goal))
                                } else {
                                    println("Please enter a valid water intake goal")
                                }
                            },
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.End),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.onTertiary,
                                contentColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text("Save")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(64.dp))
                Text(text = "Avatar", style = MaterialTheme.typography.headlineLarge)

                Spacer(modifier = Modifier.height(8.dp))


                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .width(300.dp)
                        .shadow(elevation = 10.dp, shape = MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        skinSelect(mainViewModel)
                    }
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    )
}

@Composable
fun skinSelect(mainViewModel: MainViewModel) {

    val state = mainViewModel.mainViewState.collectAsState()

    val predefinedImageIds = listOf(
        R.drawable.ic_action_avatar_1, R.drawable.ic_action_avatar_2, R.drawable.ic_action_avatar_3
    )

    val selectedImageIndex = remember { mutableStateOf(0) }

    state.value.userInfo.selectedSkin?.let {
        selectedImageIndex.value = it
    }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(predefinedImageIds) { imageId ->
            if (imageId == predefinedImageIds[state.value.userInfo.selectedSkin ?: 0] && state.value.userInfo.selectedSkin != null) {
                Box(
                    modifier = Modifier
                        .height(125.dp)
                        .width(80.dp)
                        .shadow(elevation = 10.dp, shape = MaterialTheme.shapes.medium)
                        .border(
                            4.dp,
                            MaterialTheme.colorScheme.tertiary,
                            shape = MaterialTheme.shapes.medium
                        )
                        .background(MaterialTheme.colorScheme.onSecondary)
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(6.dp)),
                        contentScale = ContentScale.FillBounds,
                        painter = painterResource(id = imageId),
                        contentDescription = stringResource(id = R.string.avatar_image)
                    )
                }
            } else {
                if (state.value.userInfo.xp >= predefinedImageIds.indexOf(imageId) * 100) {
                    Box(
                        modifier = Modifier
                            .height(125.dp)
                            .width(80.dp)
                            .shadow(elevation = 10.dp, shape = MaterialTheme.shapes.medium)
                            .background(MaterialTheme.colorScheme.onSurface)
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(6.dp))
                                .clickable(onClick = {
                                    mainViewModel.updateUser(
                                        state.value.userInfo.copy(
                                            selectedSkin = predefinedImageIds.indexOf(
                                                imageId
                                            )
                                        )
                                    )
                                }),
                            contentScale = ContentScale.FillBounds,
                            painter = painterResource(id = imageId),
                            contentDescription = stringResource(id = R.string.avatar_image)
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .height(125.dp)
                            .width(80.dp)
                            .shadow(elevation = 10.dp, shape = MaterialTheme.shapes.medium)
                            .background(MaterialTheme.colorScheme.onTertiary)
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(6.dp)),
                            contentScale = ContentScale.FillBounds,
                            painter = painterResource(id = imageId),
                            contentDescription = stringResource(id = R.string.avatar_image)
                        )
                    }
                }
            }
        }
    }
}