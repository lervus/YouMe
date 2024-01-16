package com.cc221020.ccl3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.cc221020.ccl3.data.Goal
import com.cc221020.ccl3.data.YouDatabase
import com.cc221020.ccl3.ui.theme.YouMeTheme
import com.cc221020.ccl3.view.MainView

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(this, YouDatabase::class.java, "You.db")
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YouMeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView()
                }
            }
        }
    }
}