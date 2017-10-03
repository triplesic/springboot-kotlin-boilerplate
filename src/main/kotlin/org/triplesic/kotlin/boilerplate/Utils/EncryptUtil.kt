package org.triplesic.kotlin.boilerplate.Utils

import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import java.security.spec.KeySpec
import java.util.Arrays

import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class EncryptUtil {

    @Throws(NoSuchAlgorithmException::class, InvalidKeySpecException::class)
    fun getEncryptedPassword(password: String, salt: ByteArray): ByteArray {
        // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
        // specifically names SHA-1 as an acceptable hashing algorithm for
        // PBKDF2
        val algorithm = "PBKDF2WithHmacSHA1"
        // SHA-1 generates 160 bit hashes, so that's what makes sense here
        val derivedKeyLength = 160
        // Pick an iteration count that works for you. The NIST recommends at
        // least 1,000 iterations:
        // http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
        // iOS 4.x reportedly uses 10,000:
        // http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
        val iterations = 20000
        val spec = PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength)
        val f = SecretKeyFactory.getInstance(algorithm)
        return f.generateSecret(spec).encoded
    }

    @Throws(NoSuchAlgorithmException::class)
    fun generateSalt(): ByteArray {
        // VERY important to use SecureRandom instead of just Random
        val random = SecureRandom.getInstance("SHA1PRNG")
        // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
        val salt = ByteArray(8)
        random.nextBytes(salt)
        return salt
    }

    @Throws(NoSuchAlgorithmException::class, InvalidKeySpecException::class)
    fun authenticate(attemptedPassword: String, encryptedPassword: ByteArray, salt: ByteArray): Boolean {
        // Encrypt the clear-text password using the same salt that was used to
        // encrypt the original password
        val encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt)

        // Authentication succeeds if encrypted password that the user entered
        // is equal to the stored hash
        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword)
    }

}
