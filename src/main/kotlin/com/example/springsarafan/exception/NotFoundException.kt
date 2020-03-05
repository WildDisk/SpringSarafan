package com.example.springsarafan.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 *
 *
 * @project SpringSarafan
 * @author WildDisk on 01.03.2020
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class NotFoundException : RuntimeException() {
}