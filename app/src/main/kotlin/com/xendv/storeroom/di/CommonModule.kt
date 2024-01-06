package com.xendv.storeroom.di

import com.xendv.storeroom.navigation.common.Navigator
import com.xendv.storeroom.navigation.common.NavigatorImpl
import org.koin.dsl.module

val commonModule = module {
    includes(networkModule)

    single<Navigator> {
        NavigatorImpl()
    }
}