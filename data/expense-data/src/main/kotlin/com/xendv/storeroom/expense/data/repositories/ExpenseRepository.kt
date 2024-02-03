package com.xendv.storeroom.expense.data.repositories

import com.xendv.storeroom.data.common.repository.ItemRepository
import com.xendv.storeroom.expense.data.entities.ExpenseItem

interface ExpenseRepository : ItemRepository<ExpenseItem, String>