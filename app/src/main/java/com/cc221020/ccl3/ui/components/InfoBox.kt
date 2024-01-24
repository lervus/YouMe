package com.cc221020.ccl3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
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
                .size(350.dp, 350.dp)
                .background(
                    MaterialTheme.colorScheme.secondary,
                    shape = MaterialTheme.shapes.medium
                )
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Text(text = buildAnnotatedString {
                        withStyle(style = ParagraphStyle(textAlign = TextAlign.Start)) {
                            append("Me: Your Physical health, record your daily water intake and quality of meals eaten.\n\n")
                            append("You: Your goals and todo's, set goals and add todo's to them by clicking on them.\n\n")
                            append("Change your avatar in the settings, if you have enough XP!")
                        }
                    },
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(8.dp))

                    Button(onClick = { mainViewModel.closeInfo() }, modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.medium)) {
                        Text(text = "Close")
                    }
                }
            }
        }
    }

}