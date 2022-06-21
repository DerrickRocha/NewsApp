package com.example.newsapp.ui.composables

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object NewsSourceScreen: Screen("news_source_screen")
}
