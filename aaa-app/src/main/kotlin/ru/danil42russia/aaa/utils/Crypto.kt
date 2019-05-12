package ru.danil42russia.aaa.utils

import java.math.BigInteger
import java.security.MessageDigest

/**
 * Hashes a string using SHA-256
 *
 * @param text
 *
 * @return hashed string
 */
fun sha256(text: String): String {
    //Указываем какой алгоритм использовать
    val md = MessageDigest.getInstance("SHA-256")
    //Вычисляем дайджест
    val messageDigest = md.digest(text.toByteArray())
    //Преобразуем байтовый массив в число
    val no = BigInteger(1, messageDigest)

    //Возвращаем в шестнадцатеричном формате
    return no.toString(16)
}