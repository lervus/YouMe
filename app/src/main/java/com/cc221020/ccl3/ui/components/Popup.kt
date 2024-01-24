package com.cc221020.ccl3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.cc221020.ccl3.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun InfoPopup(mainViewModel: MainViewModel) {

    val state = mainViewModel.mainViewState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if(state.value.showXpPopup){
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .background(color = MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.medium)
                    .padding(5.dp)
            ){
                Text(text = "Good Job! You earned ${state.value.xpChange}XP!")
            }
            LaunchedEffect(key1 = state.value.showXpPopup) {
                delay(3000)
                mainViewModel.closeXpPopup()
            }
        }
    }
}