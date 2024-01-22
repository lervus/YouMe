package com.cc221020.ccl3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.cc221020.ccl3.ui.components.BackButton
import com.cc221020.ccl3.ui.components.GoalList

@Composable
fun MeView(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp)) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Go Back",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(50.dp)
                    .clickable {
                        // mainViewModel.settings()
                    },
                tint = MaterialTheme.colorScheme.onTertiary
            )
            Text(
                text = "Personal",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(50.dp)
                    .clickable {
                        // mainViewModel.settings()
                    },
                tint = MaterialTheme.colorScheme.onTertiary
            )
        }
        Card(
            modifier = Modifier
                .height(200.dp)
                .width(300.dp)
                .padding(16.dp)
                .shadow(elevation = 10.dp, shape = MaterialTheme.shapes.medium),
            shape = MaterialTheme.shapes.medium
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
               // Food
                Text(text = "DIS FOOD")
            }
        }
        Spacer(modifier = Modifier.height(1.dp))
        Card(
            modifier = Modifier
                .height(200.dp)
                .width(300.dp)
                .padding(16.dp)
                .shadow(elevation = 10.dp, shape = MaterialTheme.shapes.medium),
            shape = MaterialTheme.shapes.medium
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                // Food
                Text(text = "DIS DRANK")
            }
        }

        Button(
            onClick = { },
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
}