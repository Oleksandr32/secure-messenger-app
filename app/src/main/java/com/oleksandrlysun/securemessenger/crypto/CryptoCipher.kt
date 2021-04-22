package com.oleksandrlysun.securemessenger.crypto

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class CryptoCipher private constructor(private val key: SecretKeySpec) {

    companion object {

        private const val ALGORITHM = "AES/CBC/PKCS5Padding"
        private val IV_SPEC =
            IvParameterSpec(byteArrayOf(-7, -125, 12, -113, -109, -77, -13, 87, -74, -59, 27, 53, -74, -108, 6, 54))

        @JvmStatic
        fun getInstance(secretKey: ByteArray): CryptoCipher {
            val key = SecretKeySpec(secretKey, ALGORITHM)
            return CryptoCipher(key)
        }
    }

    fun encrypt(message: String): String {
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, key, IV_SPEC)
        val encryptedMessage = cipher.doFinal(message.toByteArray())

        return Base64.encodeToString(encryptedMessage, Base64.NO_WRAP)
    }

    fun decrypt(message: String): String {
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, key, IV_SPEC)
        val decodedMessage = Base64.decode(message, Base64.NO_WRAP)
        val decryptedMessage = cipher.doFinal(decodedMessage)

        return String(decryptedMessage)
    }
}