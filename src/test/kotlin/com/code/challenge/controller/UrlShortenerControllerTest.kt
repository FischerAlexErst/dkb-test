package com.code.challenge.controller

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.junit.jupiter.Testcontainers

import com.code.challenge.dto.UrlRequestDto
import com.code.challenge.entity.UrlInfo
import com.code.challenge.repository.UrlInfoRepository
import com.fasterxml.jackson.databind.ObjectMapper

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
class UrlShortenerControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var repo: UrlInfoRepository

    @BeforeEach
    fun setup() {
        repo.save(UrlInfo(1,"https://www.google.com/1", "123"));
    }

    @Test
    fun getByHashWithExistingHashTest() {
        val response = mockMvc.perform(get("/v1/url-shortener/123"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn().response.contentAsString
        assertEquals("https://www.google.com/1", response);
    }

    @Test
    fun getByHashWithNotExistingHashTest() {
        mockMvc.perform(get("/v1/url-shortener/124"))
                .andExpect(MockMvcResultMatchers.status().isNotFound);
    }

    @Test
    fun createWithNotExistingUrlTest() {
        var urlRequestDto = UrlRequestDto("https://www.google.com/2");
        var mapper = ObjectMapper();
        mockMvc.perform(post("/v1/url-shortener")
                .content(mapper.writeValueAsString(urlRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isCreated)
        val result = repo.findByUrl("https://www.google.com/2");
       assertNotNull(result);
    }

    @Test
    fun createWithExistingUrlTest() {
        val urlRequestDto = UrlRequestDto("https://www.google.com/1");
        val mapper = ObjectMapper();
        mockMvc.perform(post("/v1/url-shortener")
                .content(mapper.writeValueAsString(urlRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isCreated)
        val result = repo.findByUrl("https://www.google.com/1");
        assertEquals("123", result?.hash);
    }
}