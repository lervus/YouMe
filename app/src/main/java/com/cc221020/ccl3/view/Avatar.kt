package com.cc221020.ccl3.view

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.cc221020.ccl3.MainViewModel
import com.cc221020.ccl3.data.Book
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.Objects

@Composable
fun Avatar(navController: NavController, mainViewModel: MainViewModel){
    var context = LocalContext.current
    var jsonReturn by remember {
        mutableStateOf(" ")
    }
    var myObj: Book

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "This is the Avatar Page")
        Row {
            Button(onClick = { navController.navigate("me") }) {
                Text(text = "Me")
            }
            Button(onClick = { navController.navigate("you") }) {
                Text(text = "You")
            }
        }
        Button(onClick = {
            jsonReturn = readJsonFromAssets(context, "sample.json")
            myObj = parseJsonToModel(jsonReturn)
            jsonReturn = myObj.title
        }) {
            Text(text = jsonReturn)
        }
    }
}

fun readJsonFromAssets(context: Context, fileName: String): String {
    return context.assets.open(fileName).bufferedReader().use { it.readText() }
}
fun parseJsonToModel(jsonString: String): Book {
    return Json.decodeFromString(jsonString)
}

fun writeJsonToFile(book: Book, filePath: String) {
    val jsonString = Json.encodeToString(book)
    File(filePath).writeText(jsonString)
}