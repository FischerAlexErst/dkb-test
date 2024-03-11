package com.code.challenge.service.impl

import java.security.MessageDigest

import org.springframework.stereotype.Service

import com.code.challenge.service.HashService

@Service
class HashServiceImpl : HashService {
    @OptIn(ExperimentalStdlibApi::class)
    override fun generate(stringValue: String): String {
        val md = MessageDigest.getInstance("MD5");
        val digest = md.digest(stringValue.toByteArray());
        return digest.toHexString()
    }
}