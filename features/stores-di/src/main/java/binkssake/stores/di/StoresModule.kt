package binkssake.stores.di

import binksake.feature.stores.api.StoresApi
import binkssake.feature.stores.di.StoresApiImpl
import org.koin.dsl.module

val storesModule = module {
    factory<StoresApi> { StoresApiImpl() }
}
