package com.example.basic_assignment.presentation.navGraph

sealed class Routes(val route: String) {
    data object HomeScreen : Routes("home_screen")
    data object SearchScreen : Routes("search_screen")
    data object DetailScreen : Routes("detail_screen")
}
