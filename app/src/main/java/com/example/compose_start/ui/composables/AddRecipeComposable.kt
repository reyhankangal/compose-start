package com.example.compose_start.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_start.R
import com.example.compose_start.model.Category
import com.example.compose_start.model.Recipe
import com.example.compose_start.model.clear
import com.example.compose_start.ui.components.TimeSelectionComposableView


@Composable
fun AddRecipe(
    onAddClick: (recipe: Recipe) -> Unit
) {
    val recipe = remember {
        mutableStateOf(
            Recipe(
                imageResource = R.drawable.ic_recipe,
                title = "",
                desc = "",
                categoryText = "",
                cookingTime = 0
            )
        )
    }

    val categoryItems = listOf(Category.MEAL, Category.DESSERT)
    val isExpanded = remember {
        mutableStateOf(false)
    }
    val isTitleError = remember {
        mutableStateOf(false)
    }
    val isDescError = remember {
        mutableStateOf(false)
    }

    val isClearSelection = remember {
        mutableStateOf(false)
    }

    val title = remember {
        mutableStateOf("")
    }

    val desc = remember {
        mutableStateOf("")
    }

    Column {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Title",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title.value,
            isError = isTitleError.value,
            onValueChange = { text -> title.value = text },
            maxLines = 1)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.clickable { isExpanded.value = true },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Select to category...",
                fontSize = 16.sp,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = recipe.value.categoryText,
                fontSize = 16.sp,
                modifier = Modifier.padding(8.dp)
            )
            Icon(Icons.Filled.ArrowDropDown, "", modifier = Modifier.size(24.dp))
            DropdownMenu(expanded = isExpanded.value, onDismissRequest = { }) {
                categoryItems.forEachIndexed { _, category ->
                    DropdownMenuItem(onClick = {
                        isExpanded.value = false
                        recipe.value.categoryText = category.name
                    }) {
                        Text(text = category.name)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        TimeSelectionComposableView(
            startingValue = 15,
            onSelectedTime = { time -> recipe.value.cookingTime = time },
            desc = "Select to cook time...",
            isClearSelection = isClearSelection.value,
            onCleared = { isClearSelection.value = false }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Description",
            modifier = Modifier.padding(8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            value = desc.value,
            isError = isDescError.value,
            onValueChange = { text -> desc.value = text },
            maxLines = 4
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                isClearSelection.value = true
                recipe.value.clear()
                isTitleError.value = false
                isDescError.value = false
            }) {
            Text(text = "Clear")
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                isTitleError.value = title.value.isEmpty()
                isDescError.value = desc.value.isEmpty()

                if (!isTitleError.value && !isDescError.value) {
                    recipe.value.apply {
                        this.title = title.value
                        this.desc = desc.value
                    }
                    onAddClick(recipe.value)
                }
            }) {
            Text(text = "Add")
        }

    }
}

@Preview
@Composable
fun DefaultAddRecipe() {
    val onAddClick: (recipe: Recipe) -> Unit = {}
    AddRecipe(onAddClick = onAddClick)
}