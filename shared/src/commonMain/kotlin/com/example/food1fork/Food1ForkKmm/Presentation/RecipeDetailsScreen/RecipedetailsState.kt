package com.example.food1fork.Food1ForkKmm.Presentation.RecipeDetailsScreen

import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.food1fork.Food1ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food1fork.Food1ForkKmm.Domain.Utils.Queue

data class RecipedetailsState (
    val isLoading:Boolean =false,
    val recipe: RecipeModel?=null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
)


