package com.xendv.storeroom.data.common.repository

interface ItemRepository<Item, Id> {

    suspend fun fetchItems(): List<Item>

    suspend fun getItem(id: Id): Item

    suspend fun deleteItem(item: Item): Boolean

    suspend fun updateItem(item: Item): Item

    suspend fun createItem(item: Item): Item
}