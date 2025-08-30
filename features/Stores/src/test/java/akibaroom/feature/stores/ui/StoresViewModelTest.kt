package binkssake.feature.stores.ui


import app.cash.turbine.test
import binkssake.core.utils.coroutines.DispatchersProvider
import binkssake.core.utils.json.Result
import binkssake.feature.stores.api.model.SakeShop
import binkssake.feature.stores.domain.usecase.FetchSakeShopsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import io.kotest.matchers.shouldBe
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
class TestDispatchersProvider(
    private val testDispatcher: TestDispatcher = StandardTestDispatcher(),
    override val main: CoroutineDispatcher = StandardTestDispatcher(testDispatcher.scheduler, "TestMain"),
    override val default: CoroutineDispatcher = StandardTestDispatcher(testDispatcher.scheduler, "TestDefault"),
    override val io: CoroutineDispatcher = StandardTestDispatcher(testDispatcher.scheduler, "TestIO"),
    override val unconfined: CoroutineDispatcher = StandardTestDispatcher(testDispatcher.scheduler, "TestUnconfined"),
) : DispatchersProvider

@ExperimentalCoroutinesApi
class TestDispatcherRule(val dispatchers: TestDispatchersProvider = TestDispatchersProvider()) : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement = object : Statement() {
        override fun evaluate() {
            if (base == null) return
            Dispatchers.setMain(dispatchers.main)
            base.evaluate()
            Dispatchers.resetMain()
        }
    }
}

@ExperimentalCoroutinesApi
class StoresViewModelTest {
    @get:Rule
    val testDispatcherRule = TestDispatcherRule()
    private val fetchSakeShopsUseCase: FetchSakeShopsUseCase = mock()
    private fun createViewModel() = StoresViewModel(
        fetchSakeShopsUseCase = fetchSakeShopsUseCase
    )
    val sampleStores = listOf(
        SakeShop(
            name = "Sake Shop 1",
            description = "A great place for sake.",
            picture = null,
            rating = 4.5,
            address = "123 Sake St, Tokyo",
            googleMapsLink = "https://maps.google.com/?q=35.6895,139.6917",
            website = "https://sakeshop1.example.com"
        ),
        SakeShop(
            name = "Sake Shop 2",
            description = "The best sake in town.",
            picture = null,
            rating = 4.8,
            address = "456 Sake Ave, Kyoto",
            googleMapsLink = "https://maps.google.com/?q=35.0116,135.7681",
            website = "https://sakeshop2.example.com"
        )
    )

    @Test
    fun `Given FetchStores action AND success THEN sakeShops`() = runTest {
        whenever(fetchSakeShopsUseCase()).thenReturn(Result.Success(sampleStores))
        val viewModel = createViewModel()
        viewModel.effects.test {
            viewModel.executeAction(StoresViewModel.Action.FetchStores)
            advanceUntilIdle()
            viewModel.state.value shouldBe
                StoresViewModel.ViewState(
                    sakeShops = sampleStores,
                    isLoading = false,
                    showError = false
                )
        }
    }
    @Test
    fun `Given FetchStores action AND error THEN showError`() = runTest {
        whenever(fetchSakeShopsUseCase()).thenReturn(Result.Error(Error("Network Error")))
        val viewModel = createViewModel()
        viewModel.effects.test {
            viewModel.executeAction(StoresViewModel.Action.FetchStores)
            advanceUntilIdle()
            viewModel.state.value shouldBe
                StoresViewModel.ViewState(
                    sakeShops = emptyList(),
                    isLoading = false,
                    showError = true
                )
        }
    }
    @Test
    fun `Given BackClicked action THEN NavigateBack effect`() = runTest {
        val viewModel = createViewModel()
        viewModel.effects.test {
            viewModel.executeAction(StoresViewModel.Action.BackClicked)
            advanceUntilIdle()
            awaitItem() shouldBe StoresViewModel.ViewEffect.NavigateBack
        }
    }
    @Test
    fun `Given DismissError action THEN hideError`() = runTest {
        whenever(fetchSakeShopsUseCase()).thenReturn(Result.Error(Error("Network Error")))
        val viewModel = createViewModel()
        viewModel.effects.test {
            viewModel.executeAction(StoresViewModel.Action.FetchStores)
            advanceUntilIdle()
            viewModel.executeAction(StoresViewModel.Action.DismissError)
            advanceUntilIdle()
            viewModel.state.value.showError shouldBe false
        }
    }
    @Test
    fun `Given SakeShopSelected action THEN OpenSakeShopDetails effect`() = runTest {
        val viewModel = createViewModel()
        viewModel.effects.test {
            viewModel.executeAction(StoresViewModel.Action.SakeShopSelected(0))
            advanceUntilIdle()
            awaitItem() shouldBe StoresViewModel.ViewEffect.OpenSakeShopDetails(0)
        }
    }
    @Test
    fun `Given AddressClicked action THEN OpenAddressInMaps effect`() = runTest {
        val address = "123 Sake St, Tokyo"
        val viewModel = createViewModel()
        viewModel.effects.test {
            viewModel.executeAction(StoresViewModel.Action.AddressClicked(address))
            advanceUntilIdle()
            awaitItem() shouldBe StoresViewModel.ViewEffect.OpenAddressInMaps(address)
        }
    }
    @Test
    fun `Given WebsiteClicked action THEN OpenWebsite effect`() = runTest {
        val website = "https://sakeshop1.example.com"
        val viewModel = createViewModel()
        viewModel.effects.test {
            viewModel.executeAction(StoresViewModel.Action.WebsiteClicked(website))
            advanceUntilIdle()
            awaitItem() shouldBe StoresViewModel.ViewEffect.OpenWebsite(website)
        }
    }
}
