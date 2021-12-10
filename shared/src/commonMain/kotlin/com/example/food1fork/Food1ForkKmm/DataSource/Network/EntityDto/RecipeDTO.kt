package com.example.food1fork.Food1ForkKmm.DataSource.Network.EntityDto
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class RecipeDTO(
    @SerialName("pk")
    var pk: Int,

    @SerialName("title")
    var title: String,

    @SerialName("publisher")
    var publisher: String,

    @SerialName("featured_image")
    var featuredImage: String,

    @SerialName("rating")
    var rating: Int = 0,

    @SerialName("source_url")
    var sourceUrl: String,

    @SerialName("ingredients")
    var ingredients: List<String> = emptyList(),

    @SerialName("long_date_added")
    var longDateAdded: Long,

    @SerialName("long_date_updated")
    var longDateUpdated: Long,
)
