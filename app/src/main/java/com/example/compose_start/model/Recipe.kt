package com.example.compose_start.model

import androidx.annotation.DrawableRes

data class Recipe(
    var id: Int = 0,
    @DrawableRes val imageResource: Int,
    var title: String,
    var desc: String,
    var categoryText: String,
    var cookingTime: Int
)

fun Recipe.clear() {
    title = ""
    desc = ""
    categoryText = ""
    cookingTime = 0
}
