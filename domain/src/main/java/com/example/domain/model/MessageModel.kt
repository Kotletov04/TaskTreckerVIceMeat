package com.example.domain.model

data class MessageModel(
    val messageId: String,
    val senderId: String,
    val senderUsername: String,
    val body: MessageBodyModel

)
