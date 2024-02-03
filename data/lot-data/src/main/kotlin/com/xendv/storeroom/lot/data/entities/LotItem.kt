package com.xendv.storeroom.lot.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("lot")
data class LotItem(
    @SerialName("id") val id: Int?,
    @SerialName("sku") val sku: String?,
    @SerialName("supplier") val supplier: Int?,
    @SerialName("supplyDate") val supplyDate: String?,
    @SerialName("expirationDate") val expirationDate: String?,
)