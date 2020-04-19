package com.example.springsarafan.util

import com.example.springsarafan.dto.EventType
import com.example.springsarafan.dto.ObjectType
import com.example.springsarafan.dto.WsEventDto
import com.example.springsarafan.model.Views
import com.fasterxml.jackson.databind.ObjectMapper
import org.codehaus.jackson.JsonProcessingException
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component
import java.util.function.BiConsumer

/**
 * @author WildDisk on 15.04.2020
 * @project SpringSarafan
 */
@Component
class WsSender(
        private val template: SimpMessagingTemplate,
        private val mapper: ObjectMapper
) {
    fun <T> sender(objectType: ObjectType, view: Class<Views.IdName>): BiConsumer<EventType, T> {
        val write = mapper
                .setConfig(mapper.serializationConfig)
                .writerWithView(view)
        return BiConsumer { eventType: EventType, payload: T ->
            val value: String
            try {
                value = write.writeValueAsString(payload)
            } catch (e: JsonProcessingException) {
                throw RuntimeException(e)
            }
            template.convertAndSend(
                    "/topic/activity",
                    WsEventDto(objectType, eventType, value)
            )
        }
    }
}