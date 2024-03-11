package com.code.challenge.service

import com.code.challenge.entity.UrlInfo

interface UrlShortenerService {
    /**
     * Get url info by hash.
     * @param hash - hash info
     * @return UrlInfo - url info
     */
    fun getByHash(hash: String): UrlInfo?

    /**
     * Create hash base on url info.
     * @param url - url
     * @return UrlInfo - url info
     */
    fun createHash(url: String): UrlInfo
}