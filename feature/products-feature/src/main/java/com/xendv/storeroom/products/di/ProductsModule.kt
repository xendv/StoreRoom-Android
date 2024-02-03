package com.xendv.storeroom.products.di

import com.xendv.storeroom.products.ProductsStateUseCases
import com.xendv.storeroom.products.ProductsUseCases
import com.xendv.storeroom.products.data.api.ProductsApi
import com.xendv.storeroom.products.data.repositories.ProductRepository
import com.xendv.storeroom.products.data.repositories.ProductRepositoryImpl
import com.xendv.storeroom.products.data.repositories.ProductStateRepository
import com.xendv.storeroom.products.data.repositories.ProductStateRepositoryImpl
import com.xendv.storeroom.products.ui.productsPage.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule = module {

    single<ProductsApi> {
        get<Retrofit>().create(ProductsApi::class.java)
    }

    single<ProductRepository> {
        ProductRepositoryImpl(
            productsApi = get()
        )
    }

    single<ProductStateRepository> {
        ProductStateRepositoryImpl()
    }
}

internal val domainModule = module {
    factoryOf(::ProductsUseCases)
    factoryOf(::ProductsStateUseCases)
}

internal val viewModelModule = module {
    viewModel { params ->
        ProductsViewModel(
            productsUseCases = get(),
            productsStateUseCases = get(),
            navigator = get(),
        )
    }
}

val productsModule = module {
    includes(dataModule, domainModule, viewModelModule)
}