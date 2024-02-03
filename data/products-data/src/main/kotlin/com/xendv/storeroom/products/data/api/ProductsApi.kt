package com.xendv.storeroom.products.data.api

import com.xendv.storeroom.products.data.entities.ProductItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductsApi {

    @GET("product")
    suspend fun getProducts(): List<ProductItem>

    @GET("product/{id}")
    suspend fun getProduct(
        @Path("id") id: String,
    ): ProductItem

    @POST("product")
    suspend fun createProduct(
        @Body item: ProductItem,
    ): ProductItem

    @PUT("product")
    suspend fun updateProduct(
        @Body item: ProductItem,
    ): ProductItem

    @DELETE("product/{id}")
    suspend fun deleteProduct(
        @Path("id") id: String
    ): Boolean
}