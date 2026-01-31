package com.example.convo.core.navigation

// This object contains all the routes available in the application to be used by the nav host
object Routes {

    const val WELCOME = "welcome"
    const val LOGIN = "login"

    const val CHATLIST = "chat_list/{username}"
    const val CHATDETAIL = "chat_detail/{chatName}"

    fun chatList(username: String): String =
        "chat_list/$username"

    fun chatDetail(chatName: String): String =
        "chat_detail/$chatName"
}
