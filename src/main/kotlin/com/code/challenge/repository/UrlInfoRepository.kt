package com.code.challenge.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import com.code.challenge.entity.UrlInfo

@Repository
interface UrlInfoRepository : CrudRepository<UrlInfo, Long> {
    /**
     * Find by hash.
     * @param hash - hash info
     * @return UrlInfo - optional url info
     */
    fun findByHash(hash: String): UrlInfo?

    /**
     * Find by url.
     * @param url - url info
     * @return UrlInfo - optional url info
     */
    fun findByUrl(url: String): UrlInfo?
}