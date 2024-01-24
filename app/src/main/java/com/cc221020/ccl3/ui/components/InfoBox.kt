package com.cc221020.ccl3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.cc221020.ccl3.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun InfoBox(mainViewModel: MainViewModel){
    
    val state = mainViewModel.mainViewState.collectAsState()
    
    if(state.value.showInfo){

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp),
            contentAlignment = Alignment.TopCenter
        ){
            Box(modifier = Modifier
                .size(150.dp)
                .background(MaterialTheme.colorScheme.secondary, shape = MaterialTheme.shapes.medium)
            ){
                Text(text = "INFOINFOINFOINFOINFOINFOINFO")
            }

            LaunchedEffect(key1 = state.value.showInfo) {
                delay(3000)
                mainViewModel.closeInfo()
            }
        }
    }

}