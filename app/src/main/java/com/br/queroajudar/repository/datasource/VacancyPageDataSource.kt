package com.br.queroajudar.repository.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.br.queroajudar.model.Vacancy
import com.br.queroajudar.network.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class VacancyPageDataSource @Inject constructor(
        private val scope: CoroutineScope,
        private val remoteDataSource: VacancyRemoteDataSource,
        private val causes:List<Int>,
        private val skills:List<Int>
) : PageKeyedDataSource<Int, Vacancy>() {

    val loadInitialResultWrapper = MutableLiveData<ResultWrapper<Any>>()
    val loadAfterResultWrapper = MutableLiveData<ResultWrapper<Any>>()
    val vacanciesSize = MutableLiveData<Int>()


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Vacancy>) {
        fetchData(1, causes, skills, loadInitialResultWrapper){
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Vacancy>) {
        fetchData(params.key, causes, skills, loadAfterResultWrapper){
            callback.onResult(it, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Vacancy>) {}

    private fun fetchData(page : Int = 1, causes:List<Int> = listOf(), skills:List<Int> = listOf(),
                          resultWrapperLiveData: MutableLiveData<ResultWrapper<Any>>,
                          callback: (List<Vacancy>) -> Unit) {

        scope.launch {
            resultWrapperLiveData.postValue(ResultWrapper.Loading)
            val getVacanciesResponse =
                remoteDataSource.fetchVacancies(page, causes, skills)
            resultWrapperLiveData.postValue(getVacanciesResponse)

            if(getVacanciesResponse is ResultWrapper.Success){
                val vacancies = getVacanciesResponse.value.data ?: listOf()
                vacanciesSize.postValue(vacanciesSize.value?.plus((vacancies.size)) ?: 0)
                callback(vacancies)
            }
        }
    }
}