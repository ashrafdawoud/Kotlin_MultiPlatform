package com.example.food1fork.Food1ForkKmm.DataSource.Cashe

import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.food1fork.Food1ForkKmm.DataSource.Domain.Utils.DatetimeUtil
import com.example.food1fork.Food1ForkKmm.DataSource.Network.KtorInterfaces.RecipeCalls.RecipeInterfaceImp.Companion.RECIPE_PAGINATION_PAGE_SIZE

class RecipeCasheImp constructor(
    val recipeDatabase: RecipeDatabase,
    private val datetimeUtil: DatetimeUtil,
): RecipeCacheInterface {
    private var queries: SqlDelightQueries = recipeDatabase.sqlDelightQueries
    override fun insert(recipe: RecipeModel) {
        queries.insertRecipe(
            id = recipe.id.toLong(),
            title = recipe.title,
            publisher = recipe.publisher,
            featured_image = recipe.featuredImage,
            rating = recipe.rating.toLong(),
            source_url = recipe.sourceUrl,
            ingredients = recipe.ingredients.convertIngredientListToString(), // TODO("Convert String to List<String>")
            date_updated = datetimeUtil.toEpochMilliseconds(recipe.dateUpdated),
            date_added = datetimeUtil.toEpochMilliseconds(recipe.dateAdded),
        )
    }

    override fun insert(recipes: List<RecipeModel>) {
        for(recipe in recipes){
            insert(recipe)
        }
    }

    override fun search(query: String, page: Int): List<RecipeModel> {
        return queries.searchRecipes(
            query = query,
            pageSize = RECIPE_PAGINATION_PAGE_SIZE.toLong(),
            offset = ((page - 1) * RECIPE_PAGINATION_PAGE_SIZE).toLong()
        ).executeAsList().toRecipeList() // TODO("convert List<Recipe_Entity> to List<Recipe>")
    }

    override fun getAll(page: Int): List<RecipeModel> {
        return queries.getAllRecipes(
            pageSize = RECIPE_PAGINATION_PAGE_SIZE.toLong(),
            offset = ((page - 1) * RECIPE_PAGINATION_PAGE_SIZE).toLong()
        ).executeAsList().toRecipeList() // TODO("convert List<Recipe_Entity> to List<Recipe>")
    }

    override fun get(recipeId: Int): RecipeModel? {
        return try {
            queries
                .getRecipeById(id = recipeId.toLong())
                .executeAsOne() .toRecipe()// TODO("convert Recipe_Entity to Recipe")
        }catch (e: NullPointerException){
            null
        }
    }
}