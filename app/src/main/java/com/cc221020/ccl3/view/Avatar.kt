package com.cc221020.ccl3.view

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221020.ccl3.MainViewModel
import com.cc221020.ccl3.R
import com.cc221020.ccl3.data.Goal
import com.cc221020.ccl3.ui.theme.YouMeTheme

@Composable
fun Avatar(navController: NavController, mainViewModel: MainViewModel){
    val buttonWidth = 150.dp
    val buttonHeight = 150.dp

    val buttonColorMe = YouMeTheme {
        MaterialTheme.colorScheme.primary
    }
    val buttonColorYou = YouMeTheme {
        MaterialTheme.colorScheme.secondary
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = stringResource(id = R.string.avatar_view))
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 1000.dp)
                .aspectRatio(1f)
                .scale(0.5f),
            painter = painterResource(id = R.drawable.ic_action_name),
            contentDescription = stringResource(id = R.string.avatar_image)
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 25.dp), // Add padding to the Row
            horizontalArrangement = Arrangement.Center)
        {
            Button(onClick = { navController.navigate("me") },
                modifier = Modifier
                    .width(buttonWidth)
                    .height(buttonHeight),
                shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 20.dp)
            ) {
                Text(text = stringResource(id = R.string.me_button))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { navController.navigate("you") },
                modifier = Modifier
                    .width(buttonWidth)
                    .height(buttonHeight),
                shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 20.dp)
            ) {
                Text(text = stringResource(id = R.string.you_button))
            }
        }
        Button(onClick = { mainViewModel.saveGoal(Goal(title = "GOALS", completed = false)) },
            modifier = Modifier
                .size(100.dp),
            shape = CircleShape) {
            Text(text = stringResource(id = R.string.goals_button))
        }
    }
}