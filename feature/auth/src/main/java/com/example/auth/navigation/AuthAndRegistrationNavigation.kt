package com.example.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import androidx.navigation.compose.navigation
import com.example.auth.AuthScreen
import com.example.auth.AuthViewModel
import com.example.auth.RegistrationScreenEmail
import com.example.auth.RegistrationScreenPassword
import com.example.auth.RegistrationScreenUsername
import com.example.auth.RegistrationScreenVerifyEmail
import com.example.mylibrary.sharedViewModel

@Serializable
data object BaseAuthAndRegistrationRoute

@Serializable
data object AuthRoute

@Serializable
data object RegistrationBaseRoute

@Serializable
data object RegistrationEmailRoute

@Serializable
data object RegistrationUsernameRoute

@Serializable
data object RegistrationPasswordRoute

@Serializable
data object RegistrationVerifyEmailRoute



fun NavController.navigateToAuthAndRegistrationScreen(navOptions: NavOptions? = null) = navigate(
    navOptions = navOptions, route = BaseAuthAndRegistrationRoute
)

fun NavController.navigateToRegistrationBaseRoute(navOptions: NavOptions? = null) = navigate(
    navOptions = navOptions, route = RegistrationBaseRoute
)

fun NavController.navigateToRegistrationEmailRoute(navOptions: NavOptions? = null) = navigate(
    navOptions = navOptions, route = RegistrationEmailRoute
)

fun NavController.navigateToRegistrationUsernameRoute(navOptions: NavOptions? = null) = navigate(
    navOptions = navOptions, route = RegistrationUsernameRoute
)

fun NavController.navigateToRegistrationPasswordRoute(navOptions: NavOptions? = null) = navigate(
    navOptions = navOptions, route = RegistrationPasswordRoute
)

fun NavController.navigateToRegistrationVerifyEmailRoute(navOptions: NavOptions? = null) = navigate(
    navOptions = navOptions, route = RegistrationVerifyEmailRoute
)

fun NavGraphBuilder.authAndRegistrationScreen(
    onClickLoginNav: () -> Unit,
    onClickToRegistrationEmail: () -> Unit,
    onClickToRegistrationUsername: () -> Unit,
    onClickToRegistrationVerifyEmail: () -> Unit,
    onClickToRegistrationPassword: () -> Unit,
    onClickConfirmRegistration: () -> Unit,
    navController: NavController
) {
    navigation<BaseAuthAndRegistrationRoute>(startDestination = AuthRoute) {
        composable<AuthRoute> {
            AuthScreen(
                onClickRegNav = onClickToRegistrationEmail,
                onClickLoginNav = onClickLoginNav
            )
        }
        composable<RegistrationEmailRoute> {
            RegistrationScreenEmail(
                viewModel = it.sharedViewModel<AuthViewModel>(navController),
                onClickNextStep = onClickToRegistrationPassword,
                onClickBack = {},
            )
        }
        composable<RegistrationPasswordRoute> {
            RegistrationScreenPassword(
                viewModel = it.sharedViewModel<AuthViewModel>(navController),
                onClickNextStep = onClickToRegistrationUsername,
                onClickBack = {},
            )
        }
        composable<RegistrationUsernameRoute> {
            RegistrationScreenUsername(
                viewModel = it.sharedViewModel<AuthViewModel>(navController),
                onClickNextStep = onClickToRegistrationVerifyEmail,
                onClickBack = {},
            )
        }
        composable<RegistrationVerifyEmailRoute> {
            RegistrationScreenVerifyEmail(
                viewModel = it.sharedViewModel<AuthViewModel>(navController),
                onClickComplete = onClickConfirmRegistration,
            )
        }
    }
}