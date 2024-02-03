package com.xendv.storeroom.products_unit.di

import com.xendv.storeroom.products_unit.ProductUnitStateUseCases
import com.xendv.storeroom.products_unit.ProductUnitUseCases
import com.xendv.storeroom.products_unit.data.api.ProductUnitsApi
import com.xendv.storeroom.products_unit.data.repositories.ProductUnitRepository
import com.xendv.storeroom.products_unit.data.repositories.ProductUnitRepositoryImpl
import com.xendv.storeroom.products_unit.data.repositories.ProductUnitStateRepository
import com.xendv.storeroom.products_unit.data.repositories.ProductUnitStateRepositoryImpl
import com.xendv.storeroom.products_unit.ui.productsPage.ProductUnitViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule = module {

    single<ProductUnitsApi> {
        get<Retrofit>().create(ProductUnitsApi::class.java)
    }

    single<ProductUnitRepository> {
        ProductUnitRepositoryImpl(
            productUnitApi = get()
        )
    }

    single<ProductUnitStateRepository> {
        ProductUnitStateRepositoryImpl()
    }
}

internal val domainModule = module {
    factoryOf(::ProductUnitUseCases)
    factoryOf(::ProductUnitStateUseCases)
}

internal val viewModelModule = module {
    viewModel { params ->
        ProductUnitViewModel(
            productUnitUseCases = get(),
            productUnitStateUseCases = get(),
            navigator = get(),
        )
    }
}

val productUnitModule = module {
    includes(dataModule, domainModule, viewModelModule)
}