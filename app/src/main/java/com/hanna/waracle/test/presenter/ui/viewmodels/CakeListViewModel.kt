package com.hanna.waracle.test.presenter.ui.viewmodels

import androidx.lifecycle.*
import com.hanna.waracle.test.data.api.Resource
import com.hanna.waracle.test.data.api.ResponseWrapper
import com.hanna.waracle.test.domain.models.entities.CakeItem
import com.hanna.waracle.test.domain.usecases.GetCakeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CakeListViewModel @Inject constructor(
    private val getCakeListUseCase: GetCakeListUseCase,
) : ViewModel() {

    val cakes: MutableLiveData<Resource<List<CakeItem>>> =
        MutableLiveData<Resource<List<CakeItem>>>(Resource.loading(null))

    val cakesSummary: LiveData<Resource<List<CakeItem>>> =
        Transformations.map(cakes) { resource ->
            return@map resource.copy(data = resource.data?.distinctBy { it.title }
                ?.sortedBy { it.title })
        }

    init {
        fetchCakes()
    }

    fun fetchCakes(isRefreshing: Boolean = false) {
        if (!isRefreshing) {
            val cakesValue = cakes.value?.data
            cakes.postValue(Resource.loading(cakesValue))//post if not being refreshed
        }
        viewModelScope.launch {
            when (val result: ResponseWrapper<List<CakeItem>> = getCakeListUseCase()) {
                is ResponseWrapper.Success -> cakes.postValue(Resource.success(result.value))
                is ResponseWrapper.Error -> cakes.postValue(
                    Resource.error(
                        result.throwable?.message.orEmpty(),
                        null
                    )
                )
            }
        }
    }
}