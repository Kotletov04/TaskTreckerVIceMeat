package com.example.tasktreckervicemeat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.tasktreckervicemeat.navigation.Routes
import com.example.tasktreckervicemeat.navigation.graph.authAndRegistrationNavigation
import com.example.tasktreckervicemeat.compose.screens.hubs.HubsScreen
import com.example.tasktreckervicemeat.navigation.graph.hubsNavigation
import com.example.tasktreckervicemeat.navigation.graph.loadingNavigation
import com.example.tasktreckervicemeat.ui.theme.TaskTreckerVIceMeatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            TaskTreckerVIceMeatTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.LoadingMain.route) {
                    loadingNavigation(navController = navController)
                    authAndRegistrationNavigation(navController = navController)
                    hubsNavigation()

                }


            }
        }
    }





}


@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}