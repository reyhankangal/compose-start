package com.example.compose_start.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose_start.model.Recipe
import com.example.compose_start.ui.navigation.ScreenNames
import com.example.compose_start.ui.navigation.navigate
import com.example.compose_start.ui.navigation.popBackStack


@Composable
fun RecipeDetail(recipe: Recipe, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Added...",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color.DarkGray
        )
        RecipeCard(recipe = recipe)
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { popBackStack(navController, ScreenNames.RECIPE_LIST) }
        ) {
            Text(text = "Return to list")
        }
    }
}