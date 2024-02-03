package com.xendv.storeroom.expense_unit.data.api

import com.xendv.storeroom.expense_unit.data.entities.ExpenseUnitItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ExpenseUnitApi {

    @GET("expenseUnit")
    suspend fun getExpenseUnits(): List<ExpenseUnitItem>

    @GET("expenseUnit/{id}")
    suspend fun getExpenseUnit(
        @Path("id") id: String,
    ): ExpenseUnitItem

    @POST("expenseUnit")
    suspend fun createExpenseUnit(
        @Body item: ExpenseUnitItem,
    ): ExpenseUnitItem

    @PUT("expenseUnit")
    suspend fun updateExpenseUnit(
        @Body item: ExpenseUnitItem,
    ): ExpenseUnitItem

    @DELETE("expenseUnit/{id}")
    suspend fun deleteExpenseUnit(
        @Path("id") id: Int
    ): Boolean
}