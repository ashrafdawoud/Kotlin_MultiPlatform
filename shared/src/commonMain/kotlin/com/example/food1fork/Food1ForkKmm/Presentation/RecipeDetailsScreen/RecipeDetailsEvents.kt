package com.example.food1fork.Food1ForkKmm.Presentation.RecipeDetailsScreen

import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.food1fork.Food1ForkKmm.Presentation.RecipeListScreem.RecipeListEvents

sealed class RecipeDetailsEvents {
    data class onLoadRecipe(val recipeId: Int):RecipeDetailsEvents()
    object OnRemoveHeadMessageFromQueue : RecipeDetailsEvents()
}