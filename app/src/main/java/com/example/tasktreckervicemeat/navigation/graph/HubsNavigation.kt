package com.example.tasktreckervicemeat.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.tasktreckervicemeat.compose.screens.hubs.HubsScreen
import com.example.tasktreckervicemeat.navigation.Routes


fun NavGraphBuilder.hubsNavigation() {
    navigation(route = Routes.HubsMain.route, startDestination = Routes.HubsList.route) {
        composable(route = Routes.HubsList.route) {
            HubsScreen()
        }
    }
}

