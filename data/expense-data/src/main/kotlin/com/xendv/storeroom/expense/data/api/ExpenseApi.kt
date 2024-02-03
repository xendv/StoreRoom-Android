package com.xendv.storeroom.expense.data.api

import com.xendv.storeroom.expense.data.entities.ExpenseItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ExpenseApi {

    @GET("expense")
    suspend fun getExpense(): List<ExpenseItem>

    @GET("expense/{id}")
    suspend fun getExpense(
        @Path("id") id: String,
    ): ExpenseItem

    @POST("expense")
    suspend fun createExpense(
        @Body item: ExpenseItem,
    ): ExpenseItem

    @PUT("expense")
    suspend fun updateExpense(
        @Body item: ExpenseItem,
    ): ExpenseItem

    @DELETE("expense/{id}")
    suspend fun deleteExpense(
        @Path("id") id: Int
    ): Boolean
}