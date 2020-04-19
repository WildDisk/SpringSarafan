package com.example.springsarafan.controller

import com.example.springsarafan.dto.EventType
import com.example.springsarafan.dto.ObjectType
import com.example.springsarafan.model.Message
import com.example.springsarafan.model.Views
import com.example.springsarafan.repository.MessageRepository
import com.example.springsarafan.util.WsSender
import com.fasterxml.jackson.annotation.JsonView
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.function.BiConsumer


/**
 *
 *
 * @project SpringSarafan
 * @author WildDisk on 01.03.2020
 */

@RestController
@RequestMapping("message")
class MessageController @Autowired constructor(
        private val messageRepository: MessageRepository,
        wsSenderR: WsSender
) {
    private val wsSender: BiConsumer<EventType, Message> = wsSenderR.sender(ObjectType.MESSAGE, Views.IdName::class.java)

    @GetMapping
    @JsonView(Views.IdName::class)
    fun list(): MutableList<Message> = messageRepository.findAll()

    @GetMapping("{id}")
    @JsonView(Views.FullMessage::class)
    fun getOne(@PathVariable("id") message: Message): Message = message

    @PostMapping
    fun create(@RequestBody message: Message): Message {
        message.creationDate = LocalDateTime.now()
        val updatedMessage = messageRepository.save(message)
        wsSender.accept(EventType.CREATE, updatedMessage)
        return updatedMessage
    }

    @PutMapping("{id}")
    fun update(
            @PathVariable("id") messageFromDb: Message,
            @RequestBody message: Message
    ): Message {
        BeanUtils.copyProperties(message, messageFromDb, "id")
        val updatedMessage = messageRepository.save(messageFromDb)
        wsSender.accept(EventType.UPDATE, updatedMessage)
        return updatedMessage
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") message: Message) {
        messageRepository.delete(message)
        wsSender.accept(EventType.REMOVE, message)
    }
}