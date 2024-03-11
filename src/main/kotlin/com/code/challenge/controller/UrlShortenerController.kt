package com.code.challenge.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.code.challenge.dto.UrlRequestDto
import com.code.challenge.dto.UrlResponseDto
import com.code.challenge.exception.NotFoundException
import com.code.challenge.service.UrlShortenerService


@RestController
@RequestMapping(UrlShortenerController.PATH)
class UrlShortenerController @Autowired constructor(private val service: UrlShortenerService) {
    companion object {
        const val PATH = "v1/url-shortener";
    }

    /**
     * Get url by hash.
     * @param hash - hash string
     * @return String - url
     */
    @GetMapping("/{hash}")
    fun getByHash(@PathVariable hash: String): ResponseEntity<String> {
        return ResponseEntity.ok(service.getByHash(hash)?.url ?: throw NotFoundException("url_not_found"));
    }

    /**
     * Create shortener url view.
     * @param urlRequest - url
     * @return UrlResponseDto - hash
     */
    @PostMapping
    fun createHash(@RequestBody urlRequest: UrlRequestDto): ResponseEntity<UrlResponseDto> {
        val urlInfo = service.createHash(urlRequest.url);
        return ResponseEntity.status(HttpStatus.CREATED).body(UrlResponseDto(hash = urlInfo.hash));
    }
}