package com.cc221020.ccl3.view

import android.content.res.Resources
import android.os.Bundle
import android.widget.Button
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.cc221020.ccl3.data.Goal
import com.cc221020.ccl3.ui.theme.Primary80
import com.cc221020.ccl3.ui.theme.Secondary80
import com.cc221020.ccl3.ui.theme.YouMeTheme
import java.io.File
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem

@Composable
fun Avatar(navController: NavController, mainViewModel: MainViewModel) {

    val buttonWidth = 150.dp
    val buttonHeight = 150.dp


    val predefinedImageIds = listOf(
        R.drawable.ic_action_avatar_1,
        R.drawable.ic_action_avatar_2,
        R.drawable.ic_action_avatar_3
    )

    var dropdownMenuVisible = remember { mutableStateOf(false) }
    var selectedImageIndex = remember { mutableStateOf(0) }
    var isAvatarCreated = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.avatar_view),
            style = MaterialTheme.typography.titleLarge)

        if (!isAvatarCreated.value) {
            Button(
                onClick = {
                    dropdownMenuVisible.value = true
                          },
                modifier = Modifier
                    .padding(top = 200.dp),
                shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 20.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 5.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(id = R.string.create_avatar_button))
            }
            DropdownMenu(
                expanded = dropdownMenuVisible.value,
                onDismissRequest = { dropdownMenuVisible.value = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onSecondary)
            ) {
                predefinedImageIds.forEachIndexed { index, imageId ->
                    DropdownMenuItem(
                        onClick = {
                            selectedImageIndex.value = index
                            isAvatarCreated.value = true
                            dropdownMenuVisible.value = false
                        },
                        text = {
                            Text("Avatar ${index + 1} ")
                        }
                    )
                }
            }
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

        Spacer(modifier = Modifier.weight(1f))

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
                shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 20.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 5.dp
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
                    defaultElevation = 10.dp,
                    pressedElevation = 5.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 20.dp)
            ) {
                Text(text = stringResource(id = R.string.you_button))
            }
        }

    }
}