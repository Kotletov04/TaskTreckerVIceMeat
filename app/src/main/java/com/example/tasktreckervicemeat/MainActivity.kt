package com.example.tasktreckervicemeat

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.auth.navigation.BaseAuthAndRegistrationRoute
import com.example.auth.navigation.authAndRegistrationScreen
import com.example.auth.navigation.navigateToRegistrationEmailRoute
import com.example.auth.navigation.navigateToRegistrationPasswordRoute
import com.example.auth.navigation.navigateToRegistrationUsernameRoute
import com.example.auth.navigation.navigateToRegistrationVerifyEmailRoute
import com.example.designsystem.theme.TaskTreckerVIceMeatTheme
import com.example.hubs.navigation.BaseHubsRoute
import com.example.hubs.navigation.hubsScreen
import com.example.hubs.navigation.navigateToBaseHubs
import com.example.profile.navigation.BaseProfileRoute
import com.example.profile.navigation.navigateToBaseProfile
import com.example.profile.navigation.profileScreen
import com.example.tasktreckervicemeat.navigation.OnlineMeatViceNavHost

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskTreckerVIceMeatTheme(
                dynamicColor = false,
                darkTheme = true
            ) {

                val mainViewModel = hiltViewModel<MainViewModel>()
                val navController = rememberNavController()
                val state = mainViewModel.state.value

                when (state.startStrategy) {
                    is StartStrategies.AnonymousStart -> {
                        OnlineMeatViceNavHost(
                            navController = navController,
                            startDestination = BaseAuthAndRegistrationRoute
                        )
                    }
                    is StartStrategies.AuthorizedStart -> {
                        OnlineMeatViceNavHost(
                            navController = navController,
                            startDestination = BaseHubsRoute
                        )
                    }
                    is StartStrategies.OfflineStart -> {
                        OnlineMeatViceNavHost(
                            navController = navController,
                            startDestination = BaseProfileRoute
                        )
                    }
                }
            }
        }
    }
}

