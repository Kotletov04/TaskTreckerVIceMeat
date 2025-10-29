package com.example.hubs.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.hubs.HubsScreen
import kotlinx.serialization.Serializable


@Serializable
data object BaseHubsRoute

@Serializable
data object HubsListRoute

fun NavController.navigateToBaseHubs(navOptions: NavOptions? = null) = navigate(
    navOptions = navOptions, route = BaseHubsRoute
)


fun NavGraphBuilder.hubsScreen(
    onClickProfile: () -> Unit,
    onClickLogout: () -> Unit
) {
    navigation<BaseHubsRoute>(startDestination = HubsListRoute) {
        composable<HubsListRoute> {
            HubsScreen(
                onClickProfile = onClickProfile,
                onClickLogout = onClickLogout,
                onClickHub = {}
            )
        }
    }
}