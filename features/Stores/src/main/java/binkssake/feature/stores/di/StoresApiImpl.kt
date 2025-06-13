package binkssake.feature.stores.di

import androidx.fragment.app.Fragment
import binkssake.feature.stores.api.StoresApi
import binkssake.feature.stores.ui.StoresFragment

class StoresApiImpl: StoresApi {
    override fun create(): Fragment {
        return StoresFragment.newInstance()
    }
}
