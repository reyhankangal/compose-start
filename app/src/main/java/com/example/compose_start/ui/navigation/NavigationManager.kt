package com.example.compose_start.ui.navigation

import androidx.navigation.NavController

fun navigate(navController: NavController, screenName: ScreenNames) {
    navController.navigate(screenName.path)
}

fun popBackStack(navController: NavController, screenName: ScreenNames?) {
    screenName?.let {
        navController.popBackStack(screenName.path, false)
    } ?: run {
        navController.popBackStack()
    }
}

fun navigateRecipeDetail(navController: NavController, recipeId: Int) {
    navController.navigate(ScreenNames.RECIPE_DETAIL.path
        .replace(
            oldValue = "{recipeId}",
            newValue = recipeId.toString()))
}