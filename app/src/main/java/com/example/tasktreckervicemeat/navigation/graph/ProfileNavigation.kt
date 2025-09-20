package com.example.tasktreckervicemeat.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tasktreckervicemeat.compose.screens.profile.ProfileScreen
import com.example.tasktreckervicemeat.compose.screens.profile.ProfileViewModel
import com.example.tasktreckervicemeat.navigation.Routes
import com.example.tasktreckervicemeat.sharedViewModel

fun NavGraphBuilder.profileNavigation(navController: NavController) {
    navigation(route = Routes.ProfileMain.route, startDestination = Routes.ProfileUser.route) {
        composable(route = Routes.ProfileUser.route) {
            val viewModel = it.sharedViewModel<ProfileViewModel>(navController = navController)
            ProfileScreen(viewModel = viewModel)
        }
    }
}