package org.triplesic.kotlin.boilerplate.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.triplesic.kotlin.boilerplate.dto.Message
import org.triplesic.kotlin.boilerplate.utils.EncryptUtil
import org.triplesic.kotlin.boilerplate.entity.Token
import org.triplesic.kotlin.boilerplate.entity.User
import org.triplesic.kotlin.boilerplate.repository.TokenRepository
import org.triplesic.kotlin.boilerplate.repository.UserRepository
import org.triplesic.kotlin.boilerplate.security.JwtTokenUtil
import java.time.LocalDateTime

@Service
class AuthenticationService @Autowired constructor(val userRepository: UserRepository, val tokenRepository: TokenRepository) {
    fun registerNewUser(user: User): Message {

        //1. check duplicate existUser
        var existUser = userRepository.findByUsername(user.username).orElse(null)
        if (existUser != null) {
            return Message(null, "username already exist", HttpStatus.BAD_REQUEST)
        } else {
            //2. encrypt password
            val passSalt = EncryptUtil().generateSalt()
            val encryptedPass = EncryptUtil().getEncryptedPassword(user.passwordNoEncrypted, passSalt)
            user.passSalt = passSalt
            user.password = encryptedPass
            user.passwordNoEncrypted = ""
            userRepository.save(user)
            return Message(mapOf("username" to user.username), "User has been created", HttpStatus.OK)
        }
    }

    fun login(user: User): Message {

        //1. check exist user
        var existUser = userRepository.findByUsername(user.username).orElse(null)
        if (existUser == null)
            return Message(null, "Username or password is incorrect", HttpStatus.BAD_REQUEST)

        //2. check correct password
        val isCorrectPassword = EncryptUtil().authenticate(user.passwordNoEncrypted, existUser.password!!, existUser.passSalt!!)
        if (!isCorrectPassword)
            return Message(null, "Username or password is incorrect", HttpStatus.BAD_REQUEST)

        //3. expire old token
        val oldTokenList = tokenRepository.findByUserIdAndExpiredDateGreaterThan(existUser.id, LocalDateTime.now())
        expiredOldToken(oldTokenList)

        //4. create token and save
        val tokenStr = JwtTokenUtil().generateToken(user, LocalDateTime.now())

        val newToken = Token()
        newToken.userId = existUser.id
        newToken.value = tokenStr
        newToken.expiredDate = LocalDateTime.now().plusDays(1L)

        val savedToken = tokenRepository.save(newToken)
        return Message(mapOf("token" to savedToken.value), "Login successful", HttpStatus.OK)
    }

    fun expiredOldToken(tokenList: Iterable<Token>) {

        tokenList.forEach(action = { token ->
            val now = LocalDateTime.now()
            token.expiredDate = now
            token.revisedDate = now
            tokenRepository.save(token)
        })

    }

    fun logout(user: User): Message {
        //1. check exist user
        var existUser = userRepository.findByUsername(user.username).orElse(null)
        if (existUser == null)
            return Message(null, "Username is incorrect", HttpStatus.BAD_REQUEST)

        //3. expire old token
        val oldTokenList = tokenRepository.findByUserIdAndExpiredDateGreaterThan(existUser.id, LocalDateTime.now())
        expiredOldToken(oldTokenList)

        return Message(mapOf("username" to user.username), "Logout successful", HttpStatus.OK)
    }

    fun isValidToken(token: String): Boolean {
        var validTokenList = tokenRepository.findByValueAndExpiredDateGreaterThan(token, LocalDateTime.now())
        if (validTokenList.count() > 0)
            return true
        return false
    }
}