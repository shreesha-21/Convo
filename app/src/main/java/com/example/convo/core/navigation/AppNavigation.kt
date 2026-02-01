package com.example.convo.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.convo.ui.chatdetails.ChatDetailScreen
import com.example.convo.ui.chatdetails.ChatDetailViewModelFactory
import com.example.convo.ui.chatlist.ChatListScreen
import com.example.convo.ui.login.LoginScreen
import com.example.convo.ui.welcome.WelcomeScreen

//  This composable handles the navigation between the components
@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.WELCOME) {

        // Welcome Screen
        composable(Routes.WELCOME) {
            WelcomeScreen(
                onGetStartedClick = {
                    navController.navigate(Routes.LOGIN)
                },
                modifier = modifier
            )
        }

        // Login Screen
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginClick = { username ->
                    // Navigate to Chat List and pass the username
                    // popUpTo("welcome") removes the previous screens from history so user can't go back to login
                    navController.navigate(Routes.chatList(username)) {
                        popUpTo(Routes.WELCOME) { inclusive = true }
                    }
                },
                modifier = modifier
            )
        }

        // Chat List Screen
        composable(
            route = Routes.CHATLIST,
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "User"

            ChatListScreen(
                // You'll need to update your ChatListScreen to accept this click
                onChatClick = { chatName ->
                    navController.navigate(Routes.chatDetail(chatName))
                },
                modifier = modifier
            )
        }

        // Chat Detail Screen
        composable(
            route = Routes.CHATDETAIL,
            arguments = listOf(navArgument("chatName") { type = NavType.StringType })
        ) { backStackEntry ->
            val chatName = backStackEntry.arguments?.getString("chatName") ?: error("username is required")

            ChatDetailScreen(
            onBackClick = { navController.popBackStack() },
            modifier = modifier,
            viewModel = viewModel(factory = ChatDetailViewModelFactory(username = chatName))
            )
        }
    }
}