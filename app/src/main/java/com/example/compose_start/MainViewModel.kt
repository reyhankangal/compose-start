package com.example.compose_start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.compose_start.model.Recipe

class MainViewModel : ViewModel() {

    private val recipeListLiveData = MutableLiveData<List<Recipe>>()
    val recipeList: LiveData<List<Recipe>>
        get() = recipeListLiveData

    fun addRecipe(recipe: Recipe) {
        val list = recipeListLiveData.value?.toMutableList() ?: mutableListOf()
        recipe.id = list.size
        list.add(recipe)
        recipeListLiveData.value = list
    }

    fun getRecipe(recipeId: Int): Recipe? {
        return recipeListLiveData.value?.get(recipeId)
    }
}