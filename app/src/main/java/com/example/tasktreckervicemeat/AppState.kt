package com.example.tasktreckervicemeat


/*
@Stable
class AppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope
) {

    private val previousDestination = mutableStateOf<NavDestination?>(null)

    val currentDestination: NavDestination?
        @Composable get() {
            val test = TopLevelDestination.entries
            // При обновлении навигации эмитит текущий пункт названчения
            // null в collectAsState означает, что в самом начале до первого эмита не определен экран
            val currentEntry = navController.currentBackStackEntryFlow.collectAsState(initial = null)

            return currentEntry.value?.destination.also {
                if (it != null) {
                    previousDestination.value = it
                }
            } ?: previousDestination.value
        }

}*/


data class AppState(
    val startStrategy: StartStrategies = StartStrategies.OfflineStart()
)
