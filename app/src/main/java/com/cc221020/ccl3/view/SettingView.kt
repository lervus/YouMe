package com.cc221020.ccl3.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221020.ccl3.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingView(navController: NavController, mainViewModel: MainViewModel) {
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

                BoxWithRadialButtonsFood(
                    modifier = Modifier
                        .height(200.dp)
                        .width(300.dp)
                        .padding(16.dp)
                        .shadow(elevation = 10.dp, shape = MaterialTheme.shapes.medium),
                    boxColor = MaterialTheme.colorScheme.secondary,
                    buttonText = "DIS FOOD"
                )

                Spacer(modifier = Modifier.height(1.dp))

                BoxWithRadialButtonsDrink(
                    modifier = Modifier
                        .height(200.dp)
                        .width(300.dp)
                        .padding(16.dp)
                        .shadow(elevation = 10.dp, shape = MaterialTheme.shapes.medium),
                    boxColor = MaterialTheme.colorScheme.secondary,
                    buttonText = "DIS DRANK"
                )

                // BackButton(navController = navController)
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    )
}