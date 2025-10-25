package com.example.tasktreckervicemeat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.auth.navigation.authAndRegistrationScreen
import com.example.auth.navigation.navigateToRegistrationEmailRoute
import com.example.auth.navigation.navigateToRegistrationPasswordRoute
import com.example.auth.navigation.navigateToRegistrationUsernameRoute
import com.example.auth.navigation.navigateToRegistrationVerifyEmailRoute
import com.example.hubs.navigation.hubsScreen
import com.example.hubs.navigation.navigateToBaseHubs
import com.example.profile.navigation.navigateToBaseProfile
import com.example.profile.navigation.profileScreen

@Composable
fun OnlineMeatViceNavHost(
    navController: NavHostController,
    startDestination: Any
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authAndRegistrationScreen(
            onClickLoginNav = navController::navigateToBaseHubs,
            onClickToRegistrationEmail = navController::navigateToRegistrationEmailRoute,
            onClickToRegistrationUsername = navController::navigateToRegistrationUsernameRoute,
            onClickToRegistrationVerifyEmail = navController::navigateToRegistrationVerifyEmailRoute,
            onClickToRegistrationPassword = navController::navigateToRegistrationPasswordRoute,
            onClickConfirmRegistration = navController::navigateToBaseHubs,
            navController = navController
        )
        hubsScreen(
            onClickProfile = navController::navigateToBaseProfile,
            onClickLogout = {},
        )
        profileScreen(
            navController = navController
        )

    }
}