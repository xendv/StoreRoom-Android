package com.xendv.storeroom.expense.di

import com.xendv.storeroom.expense.ExpenseStateUseCases
import com.xendv.storeroom.expense.ExpenseUseCases
import com.xendv.storeroom.expense.data.api.ExpenseApi
import com.xendv.storeroom.expense.data.repositories.ExpenseRepository
import com.xendv.storeroom.expense.data.repositories.ExpenseRepositoryImpl
import com.xendv.storeroom.expense.data.repositories.ExpenseStateRepository
import com.xendv.storeroom.expense.data.repositories.ExpenseStateRepositoryImpl
import com.xendv.storeroom.expense.ui.expensePage.ExpenseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule = module {

    single<ExpenseApi> {
        get<Retrofit>().create(ExpenseApi::class.java)
    }

    single<ExpenseRepository> {
        ExpenseRepositoryImpl(
            expenseApi = get()
        )
    }

    single<ExpenseStateRepository> {
        ExpenseStateRepositoryImpl()
    }
}

internal val domainModule = module {
    factoryOf(::ExpenseUseCases)
    factoryOf(::ExpenseStateUseCases)
}

internal val viewModelModule = module {
    viewModel { params ->
        ExpenseViewModel(
            expenseUseCases = get(),
            expenseStateUseCases = get(),
            navigator = get(),
        )
    }
}

val expenseModule = module {
    includes(dataModule, domainModule, viewModelModule)
}