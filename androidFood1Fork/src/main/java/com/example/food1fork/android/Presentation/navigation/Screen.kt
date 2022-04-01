package com.example.food1fork.android.Presentation.navigation

sealed class Screen (
    val rout:String
        ){
    object recipeList: Screen("recipiListScreen")
    object recipeDetails: Screen("recipiDetailScreen")

}
