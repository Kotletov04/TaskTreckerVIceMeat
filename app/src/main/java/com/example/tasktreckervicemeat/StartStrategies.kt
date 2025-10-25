package com.example.tasktreckervicemeat

sealed class StartStrategies {
    class AuthorizedStart: StartStrategies()
    class AnonymousStart: StartStrategies()
    class OfflineStart: StartStrategies()
}