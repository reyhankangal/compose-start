package com.example.compose_start

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.navigation.compose.composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose_start.ui.composables.AddRecipe
import com.example.compose_start.ui.composables.RecipeDetail
import com.example.compose_start.ui.composables.RecipeList
import com.example.compose_start.ui.navigation.ScreenNames
import com.example.compose_start.ui.navigation.navigate
import com.example.compose_start.ui.navigation.navigateRecipeDetail
import com.example.compose_start.ui.theme.ComposeStartTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStartTheme {
                val navController = rememberNavController()
                val recipeList by mainViewModel.recipeList.observeAsState(emptyList())
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = ScreenNames.RECIPE_LIST.path
                    ) {
                        composable(ScreenNames.RECIPE_LIST.path) {
                            RecipeList(
                                recipes = recipeList,
                                onAddClick = { navigate(navController, ScreenNames.ADD_RECIPE) })
                        }
                        composable(ScreenNames.ADD_RECIPE.path) {
                            AddRecipe(
                                onAddClick = { recipe ->
                                    mainViewModel.addRecipe(recipe)
                                    navigateRecipeDetail(navController, recipe.id)
                                })
                        }

                        composable(ScreenNames.RECIPE_DETAIL.path,
                            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
                        ) { backstackEntry ->
                            val recipeId = backstackEntry.arguments?.getInt("recipeId") ?: 0
                            mainViewModel.getRecipe(recipeId)?.let {
                                RecipeDetail(recipe = it, navController)
                            }
                        }
                    }
                }
            }
        }
    }
}