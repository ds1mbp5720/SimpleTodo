package com.example.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.presentation.navigation.MainDestination
import com.example.presentation.navigation.rememberMainNavController
import com.example.presentation.theme.SimpleTodoAppTheme

@Composable
fun SimpleTodoAppCompose() {
    val mainNavController = rememberMainNavController()
    SimpleTodoAppTheme {
        val mainViewModel: MainViewModel = viewModel()
        val context = LocalContext.current
        mainViewModel.getUsrInfo(context)
        NavHost(
            navController = mainNavController.navController,
            startDestination = if (mainViewModel.userInfo.value?.name?.isEmpty() == true) MainDestination.INSERT_USER
            else MainDestination.CHECK_PROFILE
        ) {
            composable(MainDestination.MAIN) { from ->
                MainScreen(
                    mainViewModel = mainViewModel,
                    onAddTaskClick = { mainNavController.navigateToTaskAdd(from) },
                    onEditTaskClick = {
                        mainNavController.navigateToTaskEdit(it, from)
                    }
                )
            }
            composable(
                "${MainDestination.TASK}/{id}",
                arguments = listOf(
                    navArgument("id") { type = NavType.LongType }
                )
            ) { navBackStackEntry ->
                val arguments = requireNotNull(navBackStackEntry.arguments)
                val id = arguments.getLong("id")
                WriteTaskScreen(
                    mainViewModel = mainViewModel,
                    screenType = TaskScreenType.EDIT,
                    id = id,
                    moveBackStack = { mainNavController.navController.navigateUp() }
                )
            }
            composable(MainDestination.TASK) { navBackStackEntry ->
                WriteTaskScreen(
                    mainViewModel = mainViewModel,
                    screenType = TaskScreenType.ADD,
                    moveBackStack = { mainNavController.navController.navigateUp() }
                )
            }
            composable(MainDestination.INSERT_USER) { from ->
                InsertScreen(
                    mainViewModel = mainViewModel,
                    onRegisterClick = { mainNavController.navigateToCheckProfile(from) }
                )
            }
            composable(MainDestination.CHECK_PROFILE) { from ->
                CheckProfileScreen(
                    mainViewModel = mainViewModel,
                    onStartClick = { mainNavController.navigateToMain(from) }
                )
            }
        }
    }
}
