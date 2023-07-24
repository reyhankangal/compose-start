package com.example.compose_start.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.compose_start.model.Recipe
import com.example.compose_start.ui.navigation.ScreenNames
import com.example.compose_start.ui.navigation.navigate
import com.example.compose_start.ui.navigation.popBackStack


@Composable
fun RecipeDetail(recipe: Recipe, navController: NavController) {
    Column() {
        RecipeCard(recipe = recipe)
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { popBackStack(navController, ScreenNames.RECIPE_LIST) }
        ) {
            Text(text = "Return to list")
        }
    }
}