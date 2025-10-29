package com.example.hubs

import com.example.hubs.state.HubsState
import com.example.hubs.state.UserState

data class MainHubsState(
    val isLoading: Boolean? = false,
    val error: String? = "",
    val hubsState: HubsState? = HubsState(),
    val userState: UserState? = UserState(),
)