package com.example.domain.util

sealed class Errors(val error: String) {
    object IOException: Errors(error = "Ошибка сети!")
    object UnknownError: Errors("Неизвестная ошибка!")
    object InternalServerError: Errors("Ошибка сервера!")
    object InvalidData: Errors("Данные указаны неверно!")
    object FirebaseAuthInvalidUserException: Errors("Такого пользователя не существует!")
    object FirebaseAuthInvalidCredentialsException: Errors("Неверный логин или пароль!")
    object CancellationException: Errors("Операция была отменена!")
    object FirebaseAuthWeakPasswordException: Errors("Пароль недостаточно надежен!")
    object FirebaseAuthUserCollisionException: Errors("Такой пользователь уже существует!")
    object NotFound: Errors("Не найдено!")

    object NoInputData: Errors("Введи хоть что-то!")

    object EmptyEmail: Errors("Введите почту!")
    object EmptyUsername: Errors("Введите ник!")
    object EmptyPassword: Errors("Введите пароль!")
    object EmptyRepeatPassword: Errors("Повторите пароль!")
    object PasswordDoNotMatch: Errors("Пароли не совпадают!")


}
