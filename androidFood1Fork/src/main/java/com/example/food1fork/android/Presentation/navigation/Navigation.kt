package com.example.food1fork.android.Presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.food1fork.android.Presentation.Screen
import com.example.food1fork.android.Presentation.navigation.RecipeDetailsScreen.RecipeDetailsViewModel
import com.example.food1fork.android.Presentation.navigation.RecipeListScreen.RecipeDetailsScreen
import com.example.food1fork.android.Presentation.navigation.RecipeListScreen.RecipeListScreen
import com.example.food1fork.android.Presentation.navigation.RecipeListScreen.RecipeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalStdlibApi
@Composable
fun Navigation(acivity:ViewModelStoreOwner) {
    var factory: ViewModelProvider.Factory
    var viewmodel:RecipeDetailsViewModel
    var id by remember { mutableStateOf(-1) }
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.recipeList.rout) {
        composable(route = Screen.recipeList.rout) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val recipeListViewModel:RecipeListViewModel= viewModel(acivity,"RecipeListViewModel",factory)
            RecipeListScreen(
                recipeListViewModel.recipelist.value,
                onselectRecipe = { recipeId ->
                    id = recipeId
                    navController.navigate(Screen.recipeDetails.rout + "/${recipeId}")

            })
        }
        composable(
            route = Screen.recipeDetails.rout + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            println("called rout details")
            factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            viewmodel= viewModel(acivity,"RecipeDetailsViewModel",factory)
            viewmodel.getrecipe(id)
            RecipeDetailsScreen(recipe = viewmodel.recipe.value)
        }
    }
}

