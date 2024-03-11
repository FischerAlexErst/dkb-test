package com.code.challenge.service.impl

import jakarta.transaction.Transactional

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.code.challenge.entity.UrlInfo
import com.code.challenge.repository.UrlInfoRepository
import com.code.challenge.service.HashService
import com.code.challenge.service.UrlShortenerService


@Service
class UrlShortenerServiceImpl @Autowired constructor(private val repository: UrlInfoRepository,
                                                     private val hashService: HashService) : UrlShortenerService {

    override fun getByHash(hash: String): UrlInfo? {
        return repository.findByHash(hash);
    }

    @Transactional
    override fun createHash(url: String): UrlInfo {
        return repository.findByUrl(url) ?: repository.save(UrlInfo(url = url, hash = hashService.generate(url)))
    }
}