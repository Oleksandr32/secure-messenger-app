package com.oleksandrlysun.securemessenger.crypto

import android.util.Base64
import java.math.BigInteger
import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.KeyAgreement
import javax.crypto.spec.DHParameterSpec

object DiffieHellman {

    private const val ALGORITHM = "DH"

    private val dhParameterSpec = DHParameterSpec(
        BigInteger("71272918346291771701310248989231604714508601324713363112962810538779636662727"),
        BigInteger("47863303421520197434914365716859582098266459451910945942784553065354610163915")
    )

    @JvmStatic
    fun generateKeyPair(): KeyPair {
        val keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM)
        keyPairGenerator.initialize(dhParameterSpec)
        return keyPairGenerator.genKeyPair()
    }

    @JvmStatic
    fun getSecret(ownKey: PrivateKey, otherKey: PublicKey): ByteArray {
        val keyAgreement = KeyAgreement.getInstance(ALGORITHM)
        keyAgreement.init(ownKey)
        keyAgreement.doPhase(otherKey, true)

        return keyAgreement.generateSecret()
    }

    @JvmStatic
    fun encodePublicKey(publicKey: PublicKey): String {
        return Base64.encodeToString(publicKey.encoded, Base64.NO_WRAP)
    }

    @JvmStatic
    fun encodePrivateKey(privateKey: PrivateKey): String {
        return Base64.encodeToString(privateKey.encoded, Base64.NO_WRAP)
    }

    @JvmStatic
    fun encodeSecretKey(secretKey: ByteArray): String {
        return Base64.encodeToString(secretKey, Base64.NO_WRAP)
    }

    @JvmStatic
    fun decodePublicKey(publicKeyString: String): PublicKey {
        val publicKeyBytes = Base64.decode(publicKeyString, Base64.NO_WRAP)
        val keyFactory = KeyFactory.getInstance(ALGORITHM)
        val keySpec = X509EncodedKeySpec(publicKeyBytes)

        return keyFactory.generatePublic(keySpec)
    }

    @JvmStatic
    fun decodePrivateKey(privateKeyString: String): PrivateKey {
        val privateKeyBytes = Base64.decode(privateKeyString, Base64.NO_WRAP)
        val keyFactory = KeyFactory.getInstance(ALGORITHM)
        val keySpec = PKCS8EncodedKeySpec(privateKeyBytes)

        return keyFactory.generatePrivate(keySpec)
    }

    @JvmStatic
    fun decodeSecretKey(secretKeyString: String): ByteArray {
        return Base64.decode(secretKeyString, Base64.NO_WRAP)
    }
}