package com.example.tasktreckervicemeat.navigation

import com.example.tasktreckervicemeat.navigation.Designations.AUTH_AND_REGISTRATION
import com.example.tasktreckervicemeat.navigation.Designations.HUBS_LIST
import com.example.tasktreckervicemeat.navigation.Designations.HUBS_MAIN
import com.example.tasktreckervicemeat.navigation.Designations.LOADING
import com.example.tasktreckervicemeat.navigation.Designations.LOADING_MAIN
import com.example.tasktreckervicemeat.navigation.Designations.LOGIN
import com.example.tasktreckervicemeat.navigation.Designations.PROFILE_MAIN
import com.example.tasktreckervicemeat.navigation.Designations.PROFILE_USER
import com.example.tasktreckervicemeat.navigation.Designations.REGISTRATION_EMAIL
import com.example.tasktreckervicemeat.navigation.Designations.REGISTRATION_EMAIL_VERIFY
import com.example.tasktreckervicemeat.navigation.Designations.REGISTRATION_PASSWORD
import com.example.tasktreckervicemeat.navigation.Designations.REGISTRATION_USERNAME


sealed class Routes(val route: String) {
    object LoadingMain: Routes(route = LOADING_MAIN)
    object Loading: Routes(route = LOADING)

    object AuthAndRegistration: Routes(route = AUTH_AND_REGISTRATION)
    object Login: Routes(route = LOGIN)
    object RegistrationEmail: Routes(route = REGISTRATION_EMAIL)
    object RegistrationUsername: Routes(route = REGISTRATION_USERNAME)
    object RegistrationPassword: Routes(route = REGISTRATION_PASSWORD)
    object RegistrationVerifyEmail: Routes(route = REGISTRATION_EMAIL_VERIFY)

    object HubsMain: Routes(route = HUBS_MAIN)
    object HubsList: Routes(route = HUBS_LIST)

    object ProfileMain: Routes(route = PROFILE_MAIN)
    object ProfileUser: Routes(route = PROFILE_USER)
}
