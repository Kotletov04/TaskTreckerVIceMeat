package com.example.tasktreckervicemeat.navigation.graph

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.tasktreckervicemeat.compose.screens.auth.AuthViewModel
import com.example.tasktreckervicemeat.compose.screens.loading.LoadingScreen
import com.example.tasktreckervicemeat.navigation.Designations.LOADING
import com.example.tasktreckervicemeat.navigation.Routes
import com.example.tasktreckervicemeat.sharedViewModel
import kotlinx.coroutines.delay


fun NavGraphBuilder.loadingNavigation(navController: NavController) {
    navigation(startDestination = Routes.Loading.route, route = Routes.LoadingMain.route) {
        composable(route = Routes.Loading.route) {
            val viewModel = it.sharedViewModel<AuthViewModel>(navController = navController)
            val permission = viewModel.state.value.permission
            LoadingScreen()
            LaunchedEffect(key1 = null) {
                viewModel.checkAuth()
                delay(2000)
            }
            LaunchedEffect(key1 = permission) {
                if (permission == true) {
                    navController.navigate(Routes.HubsMain.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
                if (permission == false) {
                    navController.navigate(Routes.AuthAndRegistration.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }

        }

    }
}