package com.example.compose_start.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_start.R
import com.example.compose_start.model.Category
import com.example.compose_start.model.Recipe


@Composable
fun RecipeCard(recipe: Recipe) {
    Surface(
        shape = RoundedCornerShape(8.dp), elevation = 8.dp, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_recipe),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Row() {
                    Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                        Text(
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            text = recipe.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = recipe.categoryText,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Magenta
                        )
                    }
                }
                Text(
                    text = "Cooking Time: ${recipe.cookingTime}",
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp,
                    color = Color.DarkGray
                )
                Text(text = recipe.desc, fontWeight = FontWeight.Normal, fontSize = 16.sp)
            }
        }
    }
}

@Preview
@Composable
fun DefaultRecipeCard() {
    RecipeCard(
        recipe = Recipe(
            imageResource = R.drawable.ic_recipe,
            title = "Title",
            desc = "Desc",
            categoryText = "MEAL",
            cookingTime = 15
        )
    )
}

@Composable
fun RecipeList(recipes: List<Recipe>, onAddClick: () -> Unit) {
    Column {
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onAddClick
        ) {
            Text(text = "Add New Recipe")
        }
        LazyColumn {
            items(recipes) { item: Recipe ->
                RecipeCard(recipe = item)
            }
        }
    }
}

@Preview
@Composable
fun DefaultRecipes() {
    val recipe = Recipe(
        imageResource = R.drawable.ic_recipe,
        title = "Title",
        categoryText = Category.DESSERT.name,
        cookingTime = 20,
        desc = "desc"
    )
    val list = listOf(recipe, recipe, recipe, recipe, recipe, recipe, recipe, recipe, recipe)
    RecipeList(recipes = list, onAddClick = {})
}