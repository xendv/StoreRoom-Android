package com.xendv.storeroom.expense_unit.data.repositories

import com.xendv.storeroom.data.common.repository.ItemRepository
import com.xendv.storeroom.expense_unit.data.entities.ExpenseUnitItem

interface ExpenseUnitRepository : ItemRepository<ExpenseUnitItem, String>