package com.example.springsarafan.config

import com.example.springsarafan.model.User
import com.example.springsarafan.repository.UserDetailsRepository
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import java.time.LocalDateTime


/**
 *
 *
 * @project SpringSarafan
 * @author WildDisk on 10.03.2020
 */
@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http
                ?.antMatcher("/**")
                ?.authorizeRequests()
                    ?.antMatchers("/", "/login**", "/js/**", "/error**")?.permitAll()
                    ?.anyRequest()?.authenticated()
                ?.and()
                    ?.logout()?.logoutSuccessUrl("/")?.permitAll()
                ?.and()
                    ?.csrf()?.disable()
    }

    @Bean
    fun principalExtractor(userDetailsRepository: UserDetailsRepository): PrincipalExtractor = PrincipalExtractor { map ->
        val id = map["sub"] as String
        val user: User = userDetailsRepository.findById(id).orElseGet {
            val newUser = User()
            newUser.id = id
            newUser.name = map["name"] as String
            newUser.email = map["email"] as String
            newUser.gender = map["gender"] as String?
            newUser.locale = map["locale"] as String?
            newUser.userpic = map["picture"] as String?
            newUser
        }
        user.lastVisit = LocalDateTime.now()
        userDetailsRepository.save(user)
    }
}