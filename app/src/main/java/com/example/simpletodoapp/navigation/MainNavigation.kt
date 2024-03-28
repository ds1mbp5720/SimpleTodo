package com.example.simpletodoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

object MainDestination {
    const val INSERT_USER = "insert_user"
    const val CHECK_PROFILE = "check_profile"
    const val TASK = "task"
    const val MAIN = "main"
}

@Composable
fun rememberMainNavController(
    navController: NavHostController = rememberNavController()
): MainNavController = remember(navController) {
    MainNavController(navController)
}

@Stable
class MainNavController(
    val navController: NavHostController
) {
    fun navigateToTask(from: NavBackStackEntry) {
        if (from.lifeCycleIsResume()) {
            navController.navigate(MainDestination.TASK)
        }
    }
    fun navigateToMain(from: NavBackStackEntry) {
        if (from.lifeCycleIsResume()) {
            navController.navigate(MainDestination.MAIN)
        }
    }
    fun navigateToInsertUser(from: NavBackStackEntry) {
        if (from.lifeCycleIsResume()) {
            navController.navigate(MainDestination.INSERT_USER)
        }
    }
    fun navigateToCheckProfile(from: NavBackStackEntry) {
        if (from.lifeCycleIsResume()) {
            navController.navigate(MainDestination.CHECK_PROFILE)
        }
    }
}

private fun NavBackStackEntry.lifeCycleIsResume() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED