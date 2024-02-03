package com.xendv.storeroom.products_unit.data.api

import com.xendv.storeroom.products_unit.data.entities.ProductUnitItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductUnitsApi {

    @GET("productUnit")
    suspend fun getProductUnits(): List<ProductUnitItem>

    @GET("productUnit/{id}")
    suspend fun getProductUnit(
        @Path("id") id: String,
    ): ProductUnitItem

    @POST("productUnit")
    suspend fun createProductUnit(
        @Body item: ProductUnitItem,
    ): ProductUnitItem

    @PUT("productUnitUnit")
    suspend fun updateProductUnit(
        @Body item: ProductUnitItem,
    ): ProductUnitItem

    @DELETE("productUnitUnit/{id}")
    suspend fun deleteProductUnit(
        @Path("id") id: String
    ): Boolean
}