package com.xendv.storeroom.lot.data.api

import com.xendv.storeroom.lot.data.entities.LotItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface LotApi {

    @GET("lot")
    suspend fun getLot(): List<LotItem>

    @GET("lot/{id}")
    suspend fun getLot(
        @Path("id") id: String,
    ): LotItem

    @POST("lot")
    suspend fun createLot(
        @Body item: LotItem,
    ): LotItem

    @PUT("lot")
    suspend fun updateLot(
        @Body item: LotItem,
    ): LotItem

    @DELETE("lot/{id}")
    suspend fun deleteLot(
        @Path("id") id: String
    ): Boolean
}