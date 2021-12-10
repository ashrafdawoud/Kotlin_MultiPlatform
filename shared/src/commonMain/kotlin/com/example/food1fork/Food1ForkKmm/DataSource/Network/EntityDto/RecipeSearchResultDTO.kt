package com.example.food1fork.Food1ForkKmm.DataSource.Network.EntityDto
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class RecipeSearchResultDTO(
    @SerialName("count")
    var count: Int,
    @SerialName("results")
    var results: List<RecipeDTO>,
)