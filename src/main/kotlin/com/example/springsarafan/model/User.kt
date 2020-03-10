package com.example.springsarafan.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 *
 *
 * @project SpringSarafan
 * @author WildDisk on 10.03.2020
 */
@Entity
@Table(name = "usr")
class User(
        @Id
        var id: String = "",
        var name: String = "",
        var userpic: String? = "",
        var email: String = "",
        var gender: String? = "",
        var locale: String? = "",
        var lastVisit: LocalDateTime = LocalDateTime.now()
)