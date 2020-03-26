package com.example.springsarafan.controller

import com.example.springsarafan.model.User
import com.example.springsarafan.repository.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 *
 *
 * @project SpringSarafan
 * @author WildDisk on 15.03.2020
 */
@Controller
@RequestMapping("/")
class MainController(
        @Autowired val messageRepository: MessageRepository
) {
    @Value("\${spring.profiles.active}")
    private lateinit var profile: String

    @GetMapping
    fun main(model: Model, @AuthenticationPrincipal user: User?): String {
        val data = HashMap<Any, Any?>()
        data["profile"] = user
        data["messages"] = messageRepository.findAll()
        model.addAttribute("frontendData", data)
        model.addAttribute("isDevMode", "dev" == profile)
        return "index"
    }
}