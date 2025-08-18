package com.example.tasktreckervicemeat

import android.Manifest
import android.content.ContentUris
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import coil3.compose.AsyncImage
import com.example.tasktreckervicemeat.navigation.Routes
import com.example.tasktreckervicemeat.navigation.graph.authAndRegistrationNavigation
import com.example.tasktreckervicemeat.compose.screens.hubs.HubsScreen
import com.example.tasktreckervicemeat.navigation.graph.hubsNavigation
import com.example.tasktreckervicemeat.navigation.graph.loadingNavigation
import com.example.tasktreckervicemeat.ui.theme.Gray31
import com.example.tasktreckervicemeat.ui.theme.TaskTreckerVIceMeatTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            TaskTreckerVIceMeatTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.LoadingMain.route) {
                    loadingNavigation(navController = navController)
                    authAndRegistrationNavigation(navController = navController)
                    hubsNavigation(navController = navController)
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