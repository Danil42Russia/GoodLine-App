package ru.danil42russia.aaa

import java.math.BigInteger
import java.security.MessageDigest

fun SHA256(text: String): String {
    val md = MessageDigest.getInstance("SHA-256")
    val messageDigest = md.digest(text.toByteArray())
    val no = BigInteger(1, messageDigest)
    var hashText = no.toString(16)
    while (hashText.length < 32) {
        hashText = "0$hashText"
    }

    return hashText
}