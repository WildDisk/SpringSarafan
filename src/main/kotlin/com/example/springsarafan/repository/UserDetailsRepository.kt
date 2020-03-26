package com.example.springsarafan.repository

import com.example.springsarafan.model.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 *
 *
 * @project SpringSarafan
 * @author WildDisk on 10.03.2020
 */
interface UserDetailsRepository : JpaRepository<User, String> {
}