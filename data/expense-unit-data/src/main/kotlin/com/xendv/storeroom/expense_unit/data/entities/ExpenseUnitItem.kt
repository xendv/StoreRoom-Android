package com.xendv.storeroom.expense_unit.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("expense_unit")
data class ExpenseUnitItem(
    @SerialName("id") val id: Int?,
    @SerialName("expense") val expense: Int?,
    @SerialName("unit") val unit: String?,
)