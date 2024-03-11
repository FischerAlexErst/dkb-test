package com.code.challenge.service

interface HashService {
    /**
     * Generate hash base on string.
     * @param stringValue - hash info
     * @return String - hash
     */
    fun generate(stringValue: String): String
}