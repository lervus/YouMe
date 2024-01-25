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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cc221020.ccl3.MainViewModel
import com.cc221020.ccl3.R

@Composable
fun DailyChallenge(mainViewModel: MainViewModel) {

    val state = mainViewModel.mainViewState.collectAsState()

    Spacer(modifier = Modifier.height(8.dp))
    if (!state.value.userInfo.dailyComplete) {
        Text(
            text = "Challenge of the day:",
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = stringResource(id = state.value.userInfo.currentDaily),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
        IconButton(onClick = {
            mainViewModel.completeDaily()
        }
        ) {
            Icon(
                Icons.Default.CheckCircle,
                "Complete",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    } else {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Daily challenge complete! Come back tomorrow for a new one!",
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))
    }


}