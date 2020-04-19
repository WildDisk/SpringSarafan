package com.example.springsarafan.dto

import com.example.springsarafan.model.Views
import com.fasterxml.jackson.annotation.JsonRawValue
import com.fasterxml.jackson.annotation.JsonView

/**
 *
 *
 * @project SpringSarafan
 * @author WildDisk on 15.04.2020
 */
@JsonView(Views.Id::class)
data class WsEventDto(
        private val objectType: ObjectType,
        private val eventType: EventType,
        @JsonRawValue
        private val body: String
)