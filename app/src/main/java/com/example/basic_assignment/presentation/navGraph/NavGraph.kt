package com.example.basic_assignment.presentation.navGraph

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.basic_assignment.SupabaseViewModel
import com.example.basic_assignment.data.model.Content
import com.example.basic_assignment.data.model.UserState
import com.example.basic_assignment.presentation.detailsScreen.DetailsScreen
import com.example.basic_assignment.presentation.homeScreen.HomeScreen
import com.example.basic_assignment.presentation.searchScreen.SearchScreen


@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()
    val viewModel = SupabaseViewModel()

    Scaffold {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController, startDestination = startDestination,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Routes.DetailScreen.route) {
                navController.previousBackStackEntry?.savedStateHandle?.get<Content?>("content")
                    ?.let { content ->
                        val context = LocalContext.current
                        DetailsScreen(content = content, onShareClicked = {
                            Intent(Intent.ACTION_SEND).also {
                                it.putExtra(Intent.EXTRA_TEXT, content.videoUrl)
                                it.type = "text/plain"
                                if (it.resolveActivity(context.packageManager) != null) {
                                    context.startActivity(it)
                                }
                            }
                        }, navigateToDetails = {
                            navigateToDetails(navController, it)
                        }, contentList = viewModel.contentList)
                    }
            }
            composable(route = Routes.HomeScreen.route) {
                when (val userState = viewModel.userState.value) {
                    is UserState.Loading -> {
                        viewModel.getContent()
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator()
                        }
                        // Show a loading indicator while fetching data
                    }

                    is UserState.Success -> {
                        HomeScreen(
                            contentList = viewModel.contentList, navigateToDetail = {
                                navigateToDetails(navController, it)
                            }, navigateToSearch = {
                                navController.navigate(Routes.SearchScreen.route)
                            }
                        )
                    }

                    is UserState.Error -> {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(text =userState.message)
                        }
                        // Show an error message
                    }
                }
            }
            composable(route = Routes.SearchScreen.route) {

                SearchScreen(
                    contentList = viewModel.contentList, navigateToDetail = {
                        navigateToDetails(navController, it)
                    }, navigateUp = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

private fun navigateToDetails(navController: NavController, content: Content) {
    navController.currentBackStackEntry?.savedStateHandle?.set(
        "content",
        content
    ) // use content instead of Content
    navController.navigate(Routes.DetailScreen.route)
}