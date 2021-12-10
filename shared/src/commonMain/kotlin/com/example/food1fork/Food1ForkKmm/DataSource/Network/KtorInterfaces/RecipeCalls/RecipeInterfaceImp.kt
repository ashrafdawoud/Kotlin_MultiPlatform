package com.example.food1fork.Food1ForkKmm.DataSource.Network.KtorInterfaces.RecipeCalls

import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.food1fork.Food1ForkKmm.DataSource.Network.EntityDto.RecipeDTO
import com.example.food1fork.Food1ForkKmm.DataSource.Network.EntityDto.RecipeSearchResultDTO
import com.example.food1fork.Food1ForkKmm.DataSource.Network.KtorClientFactory
import com.example.food1fork.Food1ForkKmm.DataSource.Network.toRecipe
import com.example.food1fork.Food1ForkKmm.DataSource.Network.toRecipeList
import io.ktor.client.*
import io.ktor.client.request.*

class RecipeInterfaceImp (
    val client: HttpClient,
    val baselink:String
        ):RecipeInterface {
    override suspend fun search(page: Int, query: String): List<RecipeModel> {
        return client.get<RecipeSearchResultDTO>{
            url("$baselink"+"search?page=$page&query=$query")
            header("Authorization", TOKEN)
        }.results.toRecipeList()
    }

    override suspend fun get(id: Int): RecipeModel {
        println("KtorTest: ${client.get<RecipeDTO>{
            url(baselink+"get/?id="+id)
            header("Authorization",TOKEN)
        }.toRecipe()}")
        return client.get<RecipeDTO>{
            url(baselink+"get/?id="+id)
            header("Authorization",TOKEN)
        }.toRecipe()
    }
    companion object{
        val BASEURL="https://food2fork.ca/api/recipe/"
        val TOKEN="Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
        val RECIPE_PAGINATION_PAGE_SIZE=30
    }
}