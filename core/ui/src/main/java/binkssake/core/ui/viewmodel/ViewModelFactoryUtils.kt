package binkssake.core.ui.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner


class ViewModelFactory<VM : ViewModel>(private val creator: () -> VM) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = creator() as T
}

inline fun <reified VM : ViewModel> Fragment.viewModels(
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    noinline customCreator: (() -> VM)? = null
): Lazy<VM> =
    viewModels(ownerProducer = ownerProducer, factoryProducer = customCreator?.let { { ViewModelFactory(it) } })
