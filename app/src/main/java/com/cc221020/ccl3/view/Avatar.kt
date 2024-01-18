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

@Composable
fun Avatar(navController: NavController, mainViewModel: MainViewModel) {

    val buttonWidth = 150.dp
    val buttonHeight = 150.dp


    val predefinedImageIds = listOf(
        R.drawable.ic_action_name
        // R.drawable.ic_action_name2
    )
    var selectedImageIndex = remember { mutableStateOf(0) }
    var isAvatarCreated = remember { mutableStateOf(false) }

    /*
        val avatarStateFile = "avatar_state.json"
        val predefinedImageIdsFile = "predefined_image_ids.json"

        fun loadAvatarState(): Boolean {
            val jsonString = File(context.filesDir, avatarStateFile).readText()
            return Gson().fromJson(jsonString, Boolean::class.java) ?: false
        }

        fun saveAvatarState(isAvatarCreated: Boolean) {
            val jsonString = Gson().toJson(isAvatarCreated)
            File(context.filesDir, avatarStateFile).writeText(jsonString)
        }

        fun loadPredefinedImageIds(): List<Int> {
            val jsonString = File(context.filesDir, predefinedImageIdsFile).readText()
            return Gson().fromJson(jsonString, object : TypeToken<List<Int>>() {}.type)
                ?: listOf(R.drawable.ic_action_name)
        }

        fun savePredefinedImageIds(predefinedImageIds: List<Int>) {
            val jsonString = Gson().toJson(predefinedImageIds)
            File(context.filesDir, predefinedImageIdsFile).writeText(jsonString)
        }

        var isAvatarCreated = remember { mutableStateOf(loadAvatarState()) }
        var predefinedImageIds = remember { mutableStateOf(loadPredefinedImageIds()) }
        var selectedImageIndex = remember { mutableStateOf(0) }
    */



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.avatar_view))

        if (!isAvatarCreated.value) {
            Button(
                onClick = {
                    isAvatarCreated.value = true
                },
                modifier = Modifier,
                shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 20.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 5.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(id = R.string.create_avatar_button))
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
                    containerColor = MaterialTheme.colorScheme.primary,
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
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 20.dp)
            ) {
                Text(text = stringResource(id = R.string.you_button))
            }
        }

    }
}

/*

@Composable
fun Avatar(navController: NavController, mainViewModel: MainViewModel) {
    val buttonWidth = 150.dp
    val buttonHeight = 150.dp

    val avatarStateFile = "avatar_state.json"
    val predefinedImageIdsFile = "predefined_image_ids.json"

    fun loadAvatarState(): Boolean {
        // Implement the logic to read from a JSON file and return the boolean value
        // Example: Use resources.openRawResource(R.raw.avatar_state) to open a JSON file in the 'res/raw' folder
        // and parse it using Gson. For simplicity, I'll provide a placeholder implementation.
        // Replace it with the actual logic based on your requirements.
        val jsonString = File(context.filesDir, avatarStateFile).readText()
        return Gson().fromJson(jsonString, Boolean::class.java) ?: false
    }

    // Function to save avatar state to a JSON file
    fun saveAvatarState(isAvatarCreated: Boolean) {
        // Implement the logic to write to a JSON file
        // Example: Use resources.openRawResource(R.raw.avatar_state) to open a JSON file in the 'res/raw' folder
        // and write the boolean value using Gson. For simplicity, I'll provide a placeholder implementation.
        // Replace it with the actual logic based on your requirements.
        val jsonString = Gson().toJson(isAvatarCreated)
        File(context.filesDir, avatarStateFile).writeText(jsonString)
    }

    // Function to load predefined image IDs from a JSON file
    fun loadPredefinedImageIds(): List<Int> {
        // Implement the logic to read from a JSON file and return the list
        // Example: Use resources.openRawResource(R.raw.predefined_image_ids) to open a JSON file in the 'res/raw' folder
        // and parse it using Gson. For simplicity, I'll provide a placeholder implementation.
        // Replace it with the actual logic based on your requirements.
        val jsonString = File(context.filesDir, predefinedImageIdsFile).readText()
        return Gson().fromJson(jsonString, object : TypeToken<List<Int>>() {}.type)
            ?: listOf(R.drawable.ic_action_name)
    }

    // Function to save predefined image IDs to a JSON file
    fun savePredefinedImageIds(predefinedImageIds: List<Int>) {
        // Implement the logic to write to a JSON file
        // Example: Use resources.openRawResource(R.raw.predefined_image_ids) to open a JSON file in the 'res/raw' folder
        // and write the list using Gson. For simplicity, I'll provide a placeholder implementation.
        // Replace it with the actual logic based on your requirements.
        val jsonString = Gson().toJson(predefinedImageIds)
        File(context.filesDir, predefinedImageIdsFile).writeText(jsonString)
    }

    // Load avatar state and predefined image IDs when the composable is created
    var isAvatarCreated = remember { mutableStateOf(loadAvatarState()) }
    var predefinedImageIds = remember { mutableStateOf(loadPredefinedImageIds()) }
    var selectedImageIndex = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.avatar_view))

        if (!isAvatarCreated.value) {
            Button(
                onClick = {
                    isAvatarCreated.value = true
                    saveAvatarState(true)
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
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(id = R.string.create_avatar_button))
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
                painter = painterResource(id = predefinedImageIds.value[selectedImageIndex.value]),
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
            // Other buttons remain unchanged

            Button(
                onClick = {
                    mainViewModel.saveGoal(Goal(title = "GOALS", completed = false))
                },
                modifier = Modifier
                    .size(75.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp,
                    pressedElevation = 1.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                ),
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    }
}

 */