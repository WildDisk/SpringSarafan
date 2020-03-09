package com.example.springsarafan.repository

import com.example.springsarafan.model.Message
import org.springframework.data.jpa.repository.JpaRepository

/**
 *
 *
 * @project SpringSarafan
 * @author WildDisk on 09.03.2020
 */
interface MessageRepository : JpaRepository<Message, Long> {
}