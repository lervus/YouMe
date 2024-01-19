package com.cc221020.ccl3.ui.theme

sealed class Screen(val route: String) {
    object First : Screen("You")
    object Second : Screen("Me")
}
