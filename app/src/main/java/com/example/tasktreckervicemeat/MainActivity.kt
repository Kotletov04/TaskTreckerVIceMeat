package com.example.tasktreckervicemeat

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.domain.model.MessageBodyModel
import com.example.domain.model.MessageModel
import com.example.tasktreckervicemeat.compose.navigation.Routes
import com.example.tasktreckervicemeat.compose.screens.auth.AuthScreen
import com.example.tasktreckervicemeat.compose.screens.auth.AuthViewModel
import com.example.tasktreckervicemeat.compose.screens.auth.RegistrationScreenEmail
import com.example.tasktreckervicemeat.compose.screens.auth.RegistrationScreenPassword
import com.example.tasktreckervicemeat.compose.screens.auth.RegistrationScreenUsername
import com.example.tasktreckervicemeat.compose.screens.auth.RegistrationScreenVerifyEmail
import com.example.tasktreckervicemeat.compose.screens.chats.ChatScreen
import com.example.tasktreckervicemeat.compose.screens.hubs.HubsScreen
import com.example.tasktreckervicemeat.ui.theme.TaskTreckerVIceMeatTheme
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.Route

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskTreckerVIceMeatTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.AuthAndRegistration.route) {
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
                    navigation(route = Routes.HubsMain.route, startDestination = Routes.HubsList.route) {
                        composable(route = Routes.HubsList.route) {
                            HubsScreen()
                        }
                    }
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