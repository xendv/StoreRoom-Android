package com.xendv.storeroom.lot

import com.xendv.storeroom.navigation.common.Navigator
import com.xendv.storeroom.lot.data.entities.LotItem
import com.xendv.storeroom.lot.data.repositories.LotStateRepository

class LotStateUseCases(
    private val lotStateRepository: LotStateRepository,
    private val navigator: Navigator,
) {
    val currentLotItem = lotStateRepository.itemToShow

    suspend fun showLotPage(
        lotItem: LotItem,
    ) {
        lotStateRepository.setItemToShow(item = lotItem)
        navigator.navigate("lot/edit")
    }
}
