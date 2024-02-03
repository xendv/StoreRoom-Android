package com.xendv.storeroom.expense.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("expense")
data class ExpenseItem(
    @SerialName("id") val id: Int?,
    @SerialName("units") val units: List<String>? = emptyList(),
    @SerialName("date") val date: String?,
)