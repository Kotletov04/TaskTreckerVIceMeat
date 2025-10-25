package com.example.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.mylibrary.sharedViewModel
import com.example.profile.ProfileScreen
import com.example.profile.ProfileViewModel
import kotlinx.serialization.Serializable

@Serializable
data object BaseProfileRoute

@Serializable
data object ProfileRoute

fun NavController.navigateToBaseProfile(navOptions: NavOptions? = null) = navigate(
    navOptions = navOptions, route = BaseProfileRoute
)

fun NavGraphBuilder.profileScreen(
    navController: NavController,

) {
    navigation<BaseProfileRoute>(startDestination = ProfileRoute) {
        composable<ProfileRoute> {
            ProfileScreen(
                viewModel = it.sharedViewModel<ProfileViewModel>(navController = navController)
            )
        }
    }
}





/*
* fun NavGraphBuilder.hubsScreen(
    onClickProfile: () -> Unit,
    onClickLogout: () -> Unit
) {
    navigation<BaseHubsRoute>(startDestination = HubsListRoute) {
        composable<HubsListRoute> {
            HubsScreen(
                onClickProfile = onClickProfile,
                onClickLogout = onClickLogout
            )
        }
    }
}*/