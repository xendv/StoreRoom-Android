package com.xendv.storeroom.products.data.api

import com.xendv.storeroom.products.data.entities.ProductItem
import retrofit2.http.GET

interface ProductsApi {

    @GET("product")
    suspend fun getProducts(): List<ProductItem>

}