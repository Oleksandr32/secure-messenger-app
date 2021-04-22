package com.oleksandrlysun.securemessenger.interactors.chat

import com.oleksandrlysun.securemessenger.api.ApiService
import com.oleksandrlysun.securemessenger.crypto.CryptoCipher
import com.oleksandrlysun.securemessenger.crypto.DiffieHellman
import com.oleksandrlysun.securemessenger.extensions.ioThread
import com.oleksandrlysun.securemessenger.interactors.base.BaseInteractor
import com.oleksandrlysun.securemessenger.interactors.chat.ChatAction.*
import com.oleksandrlysun.securemessenger.models.Chat
import com.oleksandrlysun.securemessenger.models.ChatSecret
import com.oleksandrlysun.securemessenger.models.Message
import com.oleksandrlysun.securemessenger.preferences.ChatPreferences
import com.oleksandrlysun.securemessenger.preferences.UserPreferences
import io.reactivex.Flowable

class ChatInteractor(
    apiService: ApiService,
    userPreferences: UserPreferences,
    private val chatPreferences: ChatPreferences
) : BaseInteractor(apiService, userPreferences) {

    private var cipher: CryptoCipher? = null

    override val channel = "chat"

    fun openChat(chat: Chat) {
        val secretKey: ByteArray
        val secretKeys = chatPreferences.getSecretKeys()
        val secretKeyString = secretKeys.find { it.id == chat.id }?.secretKey
        if (secretKeyString != null) {
            secretKey = DiffieHellman.decodeSecretKey(secretKeyString)
        } else {
            val privateKey = DiffieHellman.decodePrivateKey(userPreferences.privateKey!!)
            val otherPublicKey = DiffieHellman.decodePublicKey(chat.other.publicKey!!)
            secretKey = DiffieHellman.getSecret(privateKey, otherPublicKey)
            val chatSecret = ChatSecret(chat.id, DiffieHellman.encodeSecretKey(secretKey))
            chatPreferences.saveSecretKeys(secretKeys.apply { add(chatSecret) })
        }
        cipher = CryptoCipher.getInstance(secretKey)
    }


    fun subscribe(action: ChatAction, data: Any? = null) {
        subscribe(action.serialize(), data)
    }

    fun observeChat(): Flowable<Chat> {
        return apiService.observeChat().ioThread()
    }

    fun observeMessages(): Flowable<List<Message>> {
        return apiService.observeMessages()
            .ioThread()
            .map { it.sortedByDescending { message -> message.date } }
            .map { it.map { message -> message.apply { body = cipher?.decrypt(body) ?: body } } }

    }

    fun observeMessage(): Flowable<Message> {
        return apiService.observeMessage()
            .ioThread()
            .map { message -> message.apply { body = cipher?.decrypt(body) ?: body } }
    }

    fun sendMessage(chat: Chat, body: String) {
        val encryptedBody = cipher?.encrypt(body)
        if (encryptedBody != null) {
            subscribe(SEND_MESSAGE, mapOf("chatId" to chat.id.toString(), "body" to encryptedBody))
        }
    }

    private fun ChatAction.serialize(): String {
        return when (this) {
            GET -> "get"
            GET_MESSAGES -> "get-messages"
            SEND_MESSAGE -> "send-message"
        }
    }
}