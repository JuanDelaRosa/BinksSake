package binkssake.feature.stores.di

import androidx.fragment.app.Fragment
import binksake.feature.stores.api.StoresApi
import binkssake.feature.stores.ui.StoresListFragment

class StoresApiImpl: StoresApi {
    override fun create(): Fragment {
        return StoresListFragment.newInstance()
    }
}
