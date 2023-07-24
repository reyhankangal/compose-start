package com.example.compose_start.ui.navigation

enum class ScreenNames(val path: String) {
    RECIPE_LIST("recipe-list"),
    ADD_RECIPE("add-recipe"),
    RECIPE_DETAIL("recipe-detail/{recipeId}")
}