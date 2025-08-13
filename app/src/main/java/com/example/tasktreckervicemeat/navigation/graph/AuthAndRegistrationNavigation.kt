package com.example.tasktreckervicemeat.navigation.graph

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tasktreckervicemeat.navigation.Routes
import com.example.tasktreckervicemeat.compose.screens.auth.AuthScreen
import com.example.tasktreckervicemeat.compose.screens.auth.AuthViewModel
import com.example.tasktreckervicemeat.compose.screens.auth.RegistrationScreenEmail
import com.example.tasktreckervicemeat.compose.screens.auth.RegistrationScreenPassword
import com.example.tasktreckervicemeat.compose.screens.auth.RegistrationScreenUsername
import com.example.tasktreckervicemeat.compose.screens.auth.RegistrationScreenVerifyEmail
import com.example.tasktreckervicemeat.sharedViewModel


fun NavGraphBuilder.authAndRegistrationNavigation(navController: NavController) {
    navigation(route = Routes.AuthAndRegistration.route, startDestination = Routes.Login.route) {
        composable(route = Routes.Login.route) {
            val viewModel = it.sharedViewModel<AuthViewModel>(navController = navController)
            val permission = viewModel.state.value.permission
            LaunchedEffect(key1 = permission) {
                if (permission == true) {
                    navController.navigate(route = Routes.HubsMain.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }
            AuthScreen(
                viewModel = viewModel,
                onClickReg = {navController.navigate(route = Routes.RegistrationEmail.route)},
                onClickLogin = {
                    viewModel.login()
                }
            )
        }
        composable(route = Routes.RegistrationEmail.route) {
            val viewModel = it.sharedViewModel<AuthViewModel>(navController = navController)
            RegistrationScreenEmail(
                viewModel = viewModel,
                onClickNextStep = {
                    if (viewModel.checkRegistrationEmail()) {
                        navController.navigate(route = Routes.RegistrationUsername.route)
                    }},
                onClickBack = {}
            )
        }
        composable(route = Routes.RegistrationUsername.route) {
            val viewModel = it.sharedViewModel<AuthViewModel>(navController = navController)
            RegistrationScreenUsername(
                viewModel = viewModel,
                onClickNextStep = {
                    if (viewModel.checkRegistrationUsername()) {
                        navController.navigate(route = Routes.RegistrationPassword.route)
                    }},
                onClickBack = {}
            )
        }
        composable(route = Routes.RegistrationPassword.route) {
            val viewModel = it.sharedViewModel<AuthViewModel>(navController = navController)
            RegistrationScreenPassword(
                viewModel = viewModel,
                onClickNextStep = {
                    if (viewModel.checkRegistrationPassword()) {
                        viewModel.register()
                    }},
                onClickBack = {},
                onNavigate = {
                    navController.navigate(route = Routes.RegistrationVerifyEmail.route) {
                        popUpTo(0) { inclusive = true }
                    }

                }
            )
        }
        composable(route = Routes.RegistrationVerifyEmail.route) {
            val viewModel = it.sharedViewModel<AuthViewModel>(navController = navController)
            RegistrationScreenVerifyEmail(
                viewModel = viewModel,
                onClickComplete = { viewModel.verifyEmail() },
                onNavigate = {navController.navigate("test")}

            )
        }
    }
}
