package com.example.tasktreckervicemeat.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.tasktreckervicemeat.compose.screens.hubs.HubsScreen
import com.example.tasktreckervicemeat.compose.screens.hubs.HubsViewModel
import com.example.tasktreckervicemeat.navigation.Routes
import com.example.tasktreckervicemeat.sharedViewModel


fun NavGraphBuilder.hubsNavigation(navController: NavController) {
    navigation(route = Routes.HubsMain.route, startDestination = Routes.HubsList.route) {
        composable(route = Routes.HubsList.route) {
            val viewModel = it.sharedViewModel<HubsViewModel>(navController)
            HubsScreen(viewModel)
        }
    }
}

