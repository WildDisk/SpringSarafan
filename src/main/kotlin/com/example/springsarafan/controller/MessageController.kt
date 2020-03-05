package com.example.springsarafan.controller

import com.example.springsarafan.exception.NotFoundException
import org.springframework.web.bind.annotation.*
import java.util.*


/**
 *
 *
 * @project SpringSarafan
 * @author WildDisk on 01.03.2020
 */

@RestController
@RequestMapping("message")
class MessageController {
    private var counter = 4
    private val messages: ArrayList<MutableMap<String, String>> = object : ArrayList<MutableMap<String, String>>() {
        init {
            add(object : HashMap<String, String>() {
                init {
                    put("id", "1")
                    put("text", "First message")
                }
            })
            add(object : HashMap<String, String>() {
                init {
                    put("id", "2")
                    put("text", "Second message")
                }
            })
            add(object : HashMap<String, String>() {
                init {
                    put("id", "3")
                    put("text", "Third message")
                }
            })
        }
    }

    @GetMapping
    fun list(): List<MutableMap<String, String>> {
        return messages
    }

    @GetMapping("{id}")
    fun getOne(@PathVariable id: String): Map<String, String> {
        return getMessage(id)
    }

    private fun getMessage(@PathVariable id: String): MutableMap<String, String> {
        return messages.stream()
                .filter { message: Map<String, String> -> message["id"] == id }
                .findFirst()
                .orElseThrow { NotFoundException() }
    }

    @PostMapping
    fun create(@RequestBody message: MutableMap<String, String>): Map<String, String> {
        message["id"] = counter++.toString()
        messages.add(message)
        return message
    }

    @PutMapping("{id}")
    fun update(@PathVariable id: String, @RequestBody message: Map<String, String>?): Map<String, String> {
        val messageFromDb = getMessage(id)
        messageFromDb.putAll(message!!)
        messageFromDb["id"] = id
        return messageFromDb
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: String) {
        val message = getMessage(id)
        messages.remove(message)
    }
}