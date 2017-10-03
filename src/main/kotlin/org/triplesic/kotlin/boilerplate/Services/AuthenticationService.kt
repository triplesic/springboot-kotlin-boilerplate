package org.triplesic.kotlin.boilerplate.Services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.triplesic.kotlin.boilerplate.Utils.EncryptUtil
import org.triplesic.kotlin.boilerplate.entity.User
import org.triplesic.kotlin.boilerplate.repository.UserRepository

@Service
class AuthenticationService @Autowired constructor(val userRepository: UserRepository){
    fun registerNewUser(user: User): Boolean{

        //1. check duplicate existUser
        var existUser = userRepository.findByUsername(user.username).orElse(null)
        if(existUser != null) {
            return false
        }else{
            //2. encrypt password
            val passSalt = EncryptUtil().generateSalt()
            val encryptedPass = EncryptUtil().getEncryptedPassword(user.passwordNoEncrypted, passSalt)
            user.passSalt = passSalt
            user.password = encryptedPass
            user.passwordNoEncrypted = ""
            userRepository.save(user)
            return true
        }
    }
}