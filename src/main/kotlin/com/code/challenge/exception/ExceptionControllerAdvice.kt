package com.code.challenge.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    /**
     * Catch error by type NotFoundException.
     * @param ex exception
     * @return String
     */
    @ExceptionHandler
    fun handleIllegalStateException(ex: NotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message);
    }
}