package com.hanna.waracle.test.presenter.viewmodels

import com.google.common.truth.Truth.assertThat
import com.hanna.waracle.test.BaseTest
import com.hanna.waracle.test.data.api.Resource
import com.hanna.waracle.test.data.api.ResponseWrapper
import com.hanna.waracle.test.domain.models.entities.CakeItem
import com.hanna.waracle.test.domain.usecases.GetCakeListUseCase
import com.hanna.waracle.test.presenter.ui.viewmodels.CakeListViewModel
import com.hanna.waracle.test.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

//TODO("Tests here need to be upgraded, renamed, and check each scenario on its own.")
@OptIn(ExperimentalCoroutinesApi::class)
class CakeListViewModelTest : BaseTest() {

    private val useCase = mock<GetCakeListUseCase>()
    private lateinit var viewModel: CakeListViewModel

    @Before
    fun setup() {
        viewModel = CakeListViewModel(useCase)
    }

    @Test
    fun `successful get cake list`() = runSuspendTest {
        val cakeList = listOf(
            CakeItem("c101", "description1", "image1"),
            CakeItem("c101", "description1", "image1"),
            CakeItem("b102", "description2", "image2"),
            CakeItem("z103", "description3", "image3"),
        )

        whenever(useCase()).thenReturn(ResponseWrapper.Success(cakeList))

        viewModel = CakeListViewModel(useCase)

        val state1 = viewModel.cakes.getOrAwaitValue()
        assertThat(state1.status).isEqualTo(Resource.Status.LOADING)//initial loading value

        advanceUntilIdle()//waiting for fetching cakes

        val state2 = viewModel.cakes.getOrAwaitValue()
        assertThat(state2.status).isEqualTo(Resource.Status.SUCCESS)//when successful value

        val cakesSummaryState = viewModel.cakesSummary.getOrAwaitValue()
        assertThat(cakesSummaryState.data).isEqualTo(cakeList.distinctBy { it.title }
            .sortedBy { it.title })

    }

    @Test
    fun `unsuccessful get cake list`() = runSuspendTest {

        whenever(useCase()).thenReturn(ResponseWrapper.Error(Throwable("This is an error")))

        viewModel = CakeListViewModel(useCase)

        val state1 = viewModel.cakes.getOrAwaitValue()
        assertThat(state1.status).isEqualTo(Resource.Status.LOADING)//initial loading value

        advanceUntilIdle()//waiting for fetching cakes

        val state2 = viewModel.cakes.getOrAwaitValue()
        assertThat(state2.status).isEqualTo(Resource.Status.ERROR)//when successful value

        val cakesSummaryState = viewModel.cakesSummary.getOrAwaitValue()
        assertThat(cakesSummaryState.status).isEqualTo(Resource.Status.ERROR)
    }

    @Test
    fun `cake list response is filtered and sorted`() = runSuspendTest {
        val cakeList = listOf(
            CakeItem("c101", "description1", "image1"),
            CakeItem("c101", "description1", "image1"),
            CakeItem("b102", "description2", "image2"),
            CakeItem("z103", "description3", "image3"),
        )

        whenever(useCase()).thenReturn(ResponseWrapper.Success(cakeList))

        viewModel = CakeListViewModel(useCase)

        val state1 = viewModel.cakes.getOrAwaitValue()
        assertThat(state1.status).isEqualTo(Resource.Status.LOADING)//initial loading value

        advanceUntilIdle()//waiting for fetching cakes

        val state2 = viewModel.cakes.getOrAwaitValue()
        assertThat(state2.status).isEqualTo(Resource.Status.SUCCESS)//initial value

        val cakesSummaryState = viewModel.cakesSummary.getOrAwaitValue()
        assertThat(cakesSummaryState.data?.size).isEqualTo(3)
        assertThat(cakesSummaryState.data!![0].title).isEqualTo("b102")
        assertThat(cakesSummaryState.data!![1].title).isEqualTo("c101")
        assertThat(cakesSummaryState.data!![2].title).isEqualTo("z103")
    }
}