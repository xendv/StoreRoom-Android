package com.xendv.storeroom.lot.di

import com.xendv.storeroom.lot.LotStateUseCases
import com.xendv.storeroom.lot.LotUseCases
import com.xendv.storeroom.lot.data.api.LotApi
import com.xendv.storeroom.lot.data.repositories.LotRepository
import com.xendv.storeroom.lot.data.repositories.LotRepositoryImpl
import com.xendv.storeroom.lot.data.repositories.LotStateRepository
import com.xendv.storeroom.lot.data.repositories.LotStateRepositoryImpl
import com.xendv.storeroom.lot.ui.lotPage.LotViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule = module {

    single<LotApi> {
        get<Retrofit>().create(LotApi::class.java)
    }

    single<LotRepository> {
        LotRepositoryImpl(
            lotApi = get()
        )
    }

    single<LotStateRepository> {
        LotStateRepositoryImpl()
    }
}

internal val domainModule = module {
    factoryOf(::LotUseCases)
    factoryOf(::LotStateUseCases)
}

internal val viewModelModule = module {
    viewModel { params ->
        LotViewModel(
            lotUseCases = get(),
            lotStateUseCases = get(),
            navigator = get(),
        )
    }
}

val lotModule = module {
    includes(dataModule, domainModule, viewModelModule)
}