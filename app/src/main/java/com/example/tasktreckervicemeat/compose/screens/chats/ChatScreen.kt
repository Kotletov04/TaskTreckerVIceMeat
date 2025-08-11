package com.example.tasktreckervicemeat.compose.screens.chats

import android.annotation.SuppressLint
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.MessageBodyModel
import com.example.domain.model.MessageModel
import com.example.tasktreckervicemeat.compose.components.chats.MessageComponent
import com.example.tasktreckervicemeat.compose.components.chats.MessageInputComponent
import com.example.tasktreckervicemeat.compose.components.chats.MyMessageComponent
import com.example.tasktreckervicemeat.ui.theme.Black22
import com.example.tasktreckervicemeat.ui.theme.Gray31


@Composable
@Preview
fun ChatScreenTest() {
    val list = listOf<MessageModel>(
        MessageModel(messageId = "1", senderId = "1", senderUsername = "Гошная", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", file = "")),
        MessageModel(messageId = "2", senderId = "2", senderUsername = "Kotletov", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum", file = "test")),
        MessageModel(messageId = "1", senderId = "1", senderUsername = "Гошная", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", file = "")),
        MessageModel(messageId = "1", senderId = "1", senderUsername = "Гошная", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", file = "")),
        MessageModel(messageId = "2", senderId = "2", senderUsername = "Kotletov", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum", file = "test")),
        MessageModel(messageId = "1", senderId = "1", senderUsername = "Гошная", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", file = "")),
        MessageModel(messageId = "1", senderId = "1", senderUsername = "Гошная", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", file = "")),
        MessageModel(messageId = "2", senderId = "2", senderUsername = "Kotletov", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum", file = "test")),
        MessageModel(messageId = "1", senderId = "1", senderUsername = "Гошная", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", file = "")),
        MessageModel(messageId = "1", senderId = "1", senderUsername = "Гошная", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", file = "")),
        MessageModel(messageId = "2", senderId = "2", senderUsername = "Kotletov", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum", file = "test")),
        MessageModel(messageId = "1", senderId = "1", senderUsername = "Гошная", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", file = "")),
        MessageModel(messageId = "1", senderId = "1", senderUsername = "Гошная", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", file = "")),
        MessageModel(messageId = "2", senderId = "2", senderUsername = "Kotletov", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum", file = "test")),
        MessageModel(messageId = "1", senderId = "1", senderUsername = "Гошная", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", file = "")),
        MessageModel(messageId = "1", senderId = "1", senderUsername = "Гошная", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", file = "")),
        MessageModel(messageId = "2", senderId = "2", senderUsername = "Kotletov", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum", file = "test")),
        MessageModel(messageId = "1", senderId = "1", senderUsername = "Гошная", body = MessageBodyModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", file = "")),

        )
    ChatScreen(messages = list)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatScreen(messages: List<MessageModel>) {
    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Gray31, bottomBar = {MessageInput()}) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp)
                .padding(WindowInsets.systemBars.asPaddingValues()),
            reverseLayout = true,
            contentPadding = WindowInsets.navigationBars.asPaddingValues()
        ) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            items(messages) { messages ->
                if (messages.senderId == "2") {
                    MyMessageComponent(messageText = messages.body.text)
                } else {
                    MessageComponent(
                        messageText = messages.body.text,
                        username = messages.senderUsername
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

        }
    }
}

@Composable
private fun MessageInput() {
    val state = remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxWidth().navigationBarsPadding().background(Black22), contentAlignment = Alignment.TopCenter) {
        MessageInputComponent(state)
    }

}