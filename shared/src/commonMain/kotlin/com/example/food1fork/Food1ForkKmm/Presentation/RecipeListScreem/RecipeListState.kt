package com.example.food1fork.Food1ForkKmm.Presentation.RecipeListScreem

import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.food1fork.Food1ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food1fork.Food1ForkKmm.Domain.Utils.Queue

data class RecipeListState(
    val isloading:Boolean=false,
    val page:Int=1,
    val query:String="",
    val selectedCategory:FoodCategoryEnum? =null,
    val recipes:List<RecipeModel> = listOf(),
    val queue:Queue<GenericMessageInfo> = Queue(mutableListOf())
)