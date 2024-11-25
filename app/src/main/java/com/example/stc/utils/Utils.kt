package com.example.stc.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


object Utils {

    fun toMD5Hash(text: String): String {
        try {
            // Create a MessageDigest instance for MD5
            val md = MessageDigest.getInstance("MD5")

            // Perform the hashing and return the hashed data as hexadecimal string
            val digest = md.digest(text.toByteArray())
            val sb = StringBuilder()
            for (b in digest) {
                sb.append(String.format("%02x", b))
            }
            return sb.toString()
        } catch (e: NoSuchAlgorithmException) {
            // Handle the case where MD5 hashing is not available
            throw RuntimeException("MD5 hashing algorithm not available", e)
        }
    }
}