package com.xendv.storeroom

import android.app.Application
import com.xendv.storeroom.di.commonModule
import com.xendv.storeroom.expense.di.expenseModule
import com.xendv.storeroom.expense_unit.di.expenseUnitModule
import com.xendv.storeroom.lot.di.lotModule
import com.xendv.storeroom.products.di.productsModule
import com.xendv.storeroom.products_unit.di.productUnitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                commonModule,
                productsModule,
                lotModule,
                productUnitModule,
                expenseModule,
                expenseUnitModule,
            )
        }
    }
}