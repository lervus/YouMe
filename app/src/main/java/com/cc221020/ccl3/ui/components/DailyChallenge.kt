package com.cc221020.ccl3.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cc221020.ccl3.MainViewModel

@Composable
fun DailyChallenge(mainViewModel: MainViewModel){

    val state = mainViewModel.mainViewState.collectAsState()

    Spacer(modifier = Modifier.height(8.dp))
    Text(text = "Challenge of the day:")
    if(!state.value.userInfo.dailyComplete){
        Text(
            text = stringResource(id = state.value.userInfo.currentDaily),
            style = MaterialTheme.typography.titleSmall,
        )
        IconButton(onClick = {
            if(!state.value.userInfo.dailyComplete){
                mainViewModel.completeDaily()
            }
        }
        ) {
            Icon(
                Icons.Default.CheckCircle,
                "Complete",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
    else{
        Text(
            text = "You completed todays challenge! Come back tomorrow for a new Challenge.",
            style = MaterialTheme.typography.headlineSmall
        )
    }


}