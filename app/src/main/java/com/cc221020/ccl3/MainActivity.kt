package com.cc221020.ccl3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.cc221020.ccl3.data.YouDatabase
import com.cc221020.ccl3.data.YouDatabase.Companion.migration1to2
import com.cc221020.ccl3.ui.theme.Typography
import com.cc221020.ccl3.ui.theme.MaterialTheme
import com.cc221020.ccl3.view.MainView

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(this, YouDatabase::class.java, "You.db")
            .addMigrations(migration1to2)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(content = {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mainViewModel: MainViewModel by viewModels {
                        object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return MainViewModel(db.goalDao, db.todoDao, db.userDao) as T
                            }
                        }
                    }
                    MainView(mainViewModel)
                }
            }, colorScheme = colorScheme, typography = Typography)
        }
    }
}