package com.xendv.storeroom.products.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("products")
data class ProductItem(
    @SerialName("sku") val sku: String?,
    @SerialName("barcode") val barcode: String?,
    @SerialName("name") val name: String?,
    @SerialName("description") val description: String?,
    @SerialName("measurement") val measurement: String?,
)