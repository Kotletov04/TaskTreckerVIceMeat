package com.example.domain.util

object ErrorMessages {
    const val ioException = "Ошибка сети!"
    const val unknownError = "Неизвестная ошибка!"
    const val internalServerError = "Ошибка сервера!"
    const val notFound = "Персонаж не найден!"
    const val firebaseAuthInvalidUserException = "Такого пользователя не существует!"
    const val firebaseAuthInvalidCredentialsException = "Неверный логин или пароль!"
    const val cancellationException = "Операция была отменена!"
    const val firebaseAuthWeakPasswordException = "Пароль недостаточно надежен!"
    const val firebaseAuthUserCollisionException = "Такой пользователь уже существует!"

    const val emptyEmail = "Почта не указана!"
    const val emptyPassword = "Пароль не указан!"
    const val emptyUsername = "Ник не указан!"
    const val emptyRepeatPassword = "Повторите пароль!"
    const val passwordDoNotMatch = "Пароли не совпадают!"
    const val emailIsNotVerify = "Подтвердите почту!"
    const val invalidLenghtPassword = "Пароль должен быть больше 6 знаков"
}