package com.example.springsarafan.controller

import com.example.springsarafan.model.Message
import com.example.springsarafan.model.Views
import com.example.springsarafan.repository.MessageRepository
import com.fasterxml.jackson.annotation.JsonView
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.*


/**
 *
 *
 * @project SpringSarafan
 * @author WildDisk on 01.03.2020
 */

@RestController
@RequestMapping("message")
class MessageController(
        @Autowired
        private val messageRepository: MessageRepository
) {
    @GetMapping
    @JsonView(Views.IdName::class)
    fun list(): MutableList<Message> = messageRepository.findAll()

    @GetMapping("{id}")
    @JsonView(Views.FullMessage::class)
    fun getOne(@PathVariable("id") message: Message): Message = message

    @PostMapping
    fun create(@RequestBody message: Message): Message = messageRepository.save(message)

    @PutMapping("{id}")
    fun update(
            @PathVariable("id") messageFromDb: Message,
            @RequestBody message: Message
    ): Message {
        BeanUtils.copyProperties(message, messageFromDb, "id")
        return messageRepository.save(messageFromDb)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") message: Message) {
        messageRepository.delete(message)
    }

    @MessageMapping("/changeMessage")
    @SendTo("/topic/activity")
    fun change(message: Message): Message = messageRepository.save(message)
}