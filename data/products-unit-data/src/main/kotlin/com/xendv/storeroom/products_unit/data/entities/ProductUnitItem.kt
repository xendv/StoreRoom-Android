package com.xendv.storeroom.products_unit.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("products")
data class ProductUnitItem(
    @SerialName("id") val id: String?,
    @SerialName("sku") val sku: String?,
    @SerialName("lot") val lot: Int?,
)