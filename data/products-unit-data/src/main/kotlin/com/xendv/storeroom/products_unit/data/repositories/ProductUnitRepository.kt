package com.xendv.storeroom.products_unit.data.repositories

import com.xendv.storeroom.data.common.repository.ItemRepository
import com.xendv.storeroom.products_unit.data.entities.ProductUnitItem

interface ProductUnitRepository : ItemRepository<ProductUnitItem, String>