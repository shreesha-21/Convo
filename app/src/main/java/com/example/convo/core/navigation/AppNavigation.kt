package com.example.convo.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.convo.core.ui.chatdetails.ChatDetailScreen
import com.example.convo.core.ui.chatlist.ChatListScreen
import com.example.convo.core.ui.login.LoginScreen
import com.example.convo.core.ui.welcome.WelcomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {

        // 1. Welcome Screen
        composable("welcome") {
            WelcomeScreen(
                onGetStartedClick = {
                    navController.navigate("login")
                }
            )
        }

        // 2. Login Screen
        composable("login") {
            LoginScreen(
                onLoginClick = { username ->
                    // Navigate to Chat List and pass the username
                    // popUpTo("welcome") removes the previous screens from history so user can't go back to login
                    navController.navigate("chat_list/$username") {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }

        // 3. Chat List Screen (Reads the username passed from Login)
        composable(
            route = "chat_list/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "User"

            ChatListScreen(
                // You'll need to update your ChatListScreen to accept this click
                onChatClick = { chatName ->
                    navController.navigate("chat_detail/$chatName")
                }
            )
        }

        // 4. Chat Detail Screen (Reads the specific chat name)
        composable(
            route = "chat_detail/{chatName}",
            arguments = listOf(navArgument("chatName") { type = NavType.StringType })
        ) { backStackEntry ->
            val chatName = backStackEntry.arguments?.getString("chatName") ?: "Chat"

            ChatDetailScreen(
                // You'll need to update ChatDetailScreen to accept the name if you want to show "Prateek" instead of "Shashi"
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}