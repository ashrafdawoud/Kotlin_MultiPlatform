package com.example.food1fork.android.Presentation.navigation.RecipeListScreen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel

@Composable
fun RecipeDetailsScreen (
    recipe:RecipeModel?
){
Text(text = "Recipe Title = ${recipe?.title}")
}