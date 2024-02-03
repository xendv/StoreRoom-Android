package com.xendv.storeroom.lot.data.repositories

import com.xendv.storeroom.data.common.repository.ItemRepository
import com.xendv.storeroom.lot.data.entities.LotItem

interface LotRepository : ItemRepository<LotItem, String>