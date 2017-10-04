package org.triplesic.kotlin.boilerplate.Services

import com.sun.org.apache.xpath.internal.operations.Bool
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

    fun login(user: User) : Boolean{

        //1. check exist user
        var existUser = userRepository.findByUsername(user.username).orElse(null)
        println("-------- query " + user.username+ "--------")
        println(existUser)
        if(existUser == null) return false

        //2. check correct password
        val isCorrectPassword = EncryptUtil().authenticate(user.passwordNoEncrypted, existUser.password!!, existUser.passSalt!!)
        return isCorrectPassword

        //3. create token and save
        //4. return isSuccess true or false
    }
}