package com.cc221020.ccl3.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221020.ccl3.MainViewModel
import com.cc221020.ccl3.R
import com.cc221020.ccl3.ui.components.RotatingIconButton

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Avatar(navController: NavController, mainViewModel: MainViewModel) {
    val state = mainViewModel.mainViewState.collectAsState()
    val buttonWidth = 150.dp
    val buttonHeight = 150.dp
    val predefinedImageIds = listOf(
        R.drawable.ic_action_avatar_1,
        R.drawable.ic_action_avatar_2,
        R.drawable.ic_action_avatar_3
    )
    val predefinedSadImageIds = listOf(
        R.drawable.ic_action_avatar_1_sad,
        R.drawable.ic_action_avatar_2_sad,
        R.drawable.ic_action_avatar_3_sad
    )
    val isVisible = remember { mutableStateOf(false) }
    val selectedImageIndex = remember { mutableStateOf(0) }
    val isAvatarCreated = remember { mutableStateOf(false) }

    mainViewModel.checkTime()
    if (state.value.userInfo.dailyComplete != null) {
        mainViewModel.calcWellB()
    }

    state.value.userInfo.selectedSkin?.let {
        isAvatarCreated.value = true
        selectedImageIndex.value = it
    }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.avatar_view),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { mainViewModel.openInfo() }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Information Button",
                            modifier = Modifier
                                .size(50.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    RotatingIconButton(
                        onClick = { navController.navigate("settings") },
                        imageVector = Icons.Filled.Settings
                    )
                },

                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
             Text(
            text = "Well-being score:"
        )
            if (state.value.userInfo.wellBeingScore > 20) {
                Text(
                    text = "Good",
                    color = Color.Green,
                    style = MaterialTheme.typography.titleLarge
                )
            } else if (state.value.userInfo.wellBeingScore > 10) {
                Text(
                    text = "Medium",
                    color = Color.Yellow,
                    style = MaterialTheme.typography.titleLarge
                )
            } else {
                Text(
                    text = "Bad",
                    color = Color.Red,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            if (!isAvatarCreated.value) {
                Button(
                    onClick = {
                        if (state.value.userInfo.xp < 100) {
                            selectedImageIndex.value = 0
                            mainViewModel.updateUser(state.value.userInfo.copy(selectedSkin = selectedImageIndex.value))
                            isAvatarCreated.value = true
                        } else {
                            isVisible.value = true
                        }
                    }, modifier = Modifier.padding(top = 200.dp), shape = RoundedCornerShape(
                        topStart = 10.dp, topEnd = 10.dp, bottomStart = 20.dp, bottomEnd = 20.dp
                    ), elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp, pressedElevation = 5.dp
                    ), colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = stringResource(id = R.string.create_avatar_button))
                }
                Spacer(modifier = Modifier.height(136.dp))
            } else {
                if (state.value.userInfo.wellBeingScore < 14) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 1000.dp)
                            .aspectRatio(1f)
                            .scale(0.5f)
                            .padding(8.dp)
                            .clip(MaterialTheme.shapes.medium),
                        painter = painterResource(id = predefinedSadImageIds[selectedImageIndex.value]),
                        contentDescription = stringResource(id = R.string.avatar_image)
                    )
                } else {
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
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 25.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        navController.navigate("me")
                    },
                    modifier = Modifier
                        .width(buttonWidth)
                        .height(buttonHeight),
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomStart = 20.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp, pressedElevation = 5.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = stringResource(id = R.string.me_button))
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        navController.navigate("you")
                    },
                    modifier = Modifier
                        .width(buttonWidth)
                        .height(buttonHeight),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp, pressedElevation = 5.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 20.dp)
                ) {
                    Text(text = stringResource(id = R.string.you_button))
                }
            }
        }
    }
}
