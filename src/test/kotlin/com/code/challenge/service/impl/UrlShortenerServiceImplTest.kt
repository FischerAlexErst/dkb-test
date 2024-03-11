package com.code.challenge.service.impl

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.mockito.*
import org.mockito.Mockito.*

import com.code.challenge.entity.UrlInfo
import com.code.challenge.repository.UrlInfoRepository
import com.code.challenge.service.HashService

class UrlShortenerServiceImplTest {
    @Mock
    private lateinit var repository: UrlInfoRepository;
    @Mock
    private lateinit var hashService: HashService;
    @InjectMocks
    private lateinit var serviceImpl: UrlShortenerServiceImpl;

    @BeforeEach
    fun init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    fun getByHashTest() {
        Mockito.`when`(repository.findByHash(ArgumentMatchers.anyString())).thenReturn(UrlInfo(1, "url", "hash"));
        val result = serviceImpl.getByHash("hash");
        Assertions.assertNotNull(result);
    }

    @Test
    fun createWithNotExistingUrlTest() {
        Mockito.`when`(repository.findByUrl(ArgumentMatchers.anyString())).thenReturn(null);
        Mockito.`when`(repository.save(ArgumentMatchers.any())).thenReturn(UrlInfo(456, "url", "hash"));
        Mockito.`when`(hashService.generate(ArgumentMatchers.anyString())).thenReturn("hash");

        val result = serviceImpl.createHash("url");
        Assertions.assertNotNull(result);
        verify(repository, atLeastOnce()).save(ArgumentMatchers.any());
    }

    @Test
    fun createWithExistingUrlTest() {
        Mockito.`when`(repository.findByUrl(ArgumentMatchers.anyString())).thenReturn(UrlInfo(1234, "url", "hash"));

        val result = serviceImpl.createHash("url");
        Assertions.assertNotNull(result);
        verify(repository, never()).save(ArgumentMatchers.any());
    }


}