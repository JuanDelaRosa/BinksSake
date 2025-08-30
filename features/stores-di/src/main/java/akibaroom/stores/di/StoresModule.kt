package akibaroom.stores.di

import akibaroom.feature.stores.api.StoresApi
import akibaroom.feature.stores.di.StoresApiImpl
import org.koin.dsl.module

val storesModule = module {
    factory<StoresApi> { StoresApiImpl() }
}
