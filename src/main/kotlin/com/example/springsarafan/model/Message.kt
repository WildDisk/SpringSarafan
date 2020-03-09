package com.example.springsarafan.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonView
import java.time.LocalDateTime
import javax.persistence.*

/**
 *
 *
 * @project SpringSarafan
 * @author WildDisk on 09.03.2020
 */
@Entity
@Table
class Message(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @JsonView(Views.IdName::class)
        val id: Long = 0,
        @JsonView(Views.IdName::class)
        var text: String = "",
        @Column(updatable = false)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonView(Views.FullMessage::class)
        val creationDate: LocalDateTime = LocalDateTime.now()
)