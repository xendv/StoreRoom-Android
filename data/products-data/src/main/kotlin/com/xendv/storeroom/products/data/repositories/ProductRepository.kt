package com.xendv.storeroom.products.data.repositories

import com.xendv.storeroom.data.common.repository.ItemRepository
import com.xendv.storeroom.products.data.entities.ProductItem

interface ProductRepository : ItemRepository<ProductItem, String>