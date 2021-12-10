package com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model
import kotlinx.datetime.*
import kotlinx.datetime.TimeZone

data class RecipeModel(
    val id: Int,
    val title: String,
    val publisher: String,
    val featuredImage: String,
    val rating: Int,
    val sourceUrl: String,
    val ingredients: List<String> = listOf(),
    val dateAdded: LocalDateTime,
    val dateUpdated: LocalDateTime,
)
