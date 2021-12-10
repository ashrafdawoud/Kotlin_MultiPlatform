package com.example.food1fork.android.Presentation

sealed class Screen (
    val rout:String
        ){
    object recipeList: Screen("recipiListScreen")
    object recipeDetails: Screen("recipiDetailScreen")

}
