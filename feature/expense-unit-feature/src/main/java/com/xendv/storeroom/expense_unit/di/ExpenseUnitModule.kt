package com.xendv.storeroom.expense_unit.di

import com.xendv.storeroom.expense_unit.ExpenseUnitStateUseCases
import com.xendv.storeroom.expense_unit.ExpenseUnitUseCases
import com.xendv.storeroom.expense_unit.data.api.ExpenseUnitApi
import com.xendv.storeroom.expense_unit.data.repositories.ExpenseUnitRepository
import com.xendv.storeroom.expense_unit.data.repositories.ExpenseUnitRepositoryImpl
import com.xendv.storeroom.expense_unit.data.repositories.ExpenseUnitStateRepository
import com.xendv.storeroom.expense_unit.data.repositories.ExpenseUnitStateRepositoryImpl
import com.xendv.storeroom.expense_unit.ui.expenseUnitPage.ExpenseUnitViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule = module {

    single<ExpenseUnitApi> {
        get<Retrofit>().create(ExpenseUnitApi::class.java)
    }

    single<ExpenseUnitRepository> {
        ExpenseUnitRepositoryImpl(
            expenseUnitApi = get()
        )
    }

    single<ExpenseUnitStateRepository> {
        ExpenseUnitStateRepositoryImpl()
    }
}

internal val domainModule = module {
    factoryOf(::ExpenseUnitUseCases)
    factoryOf(::ExpenseUnitStateUseCases)
}

internal val viewModelModule = module {
    viewModel { params ->
        ExpenseUnitViewModel(
            productsUseCases = get(),
            productsStateUseCases = get(),
            navigator = get(),
        )
    }
}

val expenseUnitModule = module {
    includes(dataModule, domainModule, viewModelModule)
}