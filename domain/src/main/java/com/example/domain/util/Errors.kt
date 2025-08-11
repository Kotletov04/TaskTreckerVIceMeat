package com.example.domain.util

sealed class Errors(val error: String) {
    object IOException: Errors(error = "Ошибка сети!")
    object UnknownError: Errors("Неизвестная ошибка!")
    object InternalServerError: Errors("Ошибка сервера!")
    object NotFound: Errors("Персонаж не найден!")
    object FirebaseAuthInvalidUserException: Errors("Такого пользователя не существует!")
    object FirebaseAuthInvalidCredentialsException: Errors("Неверный логин или пароль!")
    object CancellationException: Errors("Операция была отменена!")
    object FirebaseAuthWeakPasswordException: Errors("Пароль недостаточно надежен!")
    object FirebaseAuthUserCollisionException: Errors("Такой пользователь уже существует!")
}