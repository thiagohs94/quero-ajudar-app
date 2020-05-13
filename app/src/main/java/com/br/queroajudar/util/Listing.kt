package com.br.queroajudar.util

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.br.queroajudar.network.ResultWrapper

/**
 * Data class that is necessary for a UI to show a listing and interact w/ the rest of the system
 */
data class PagedListing<T>(
    // the LiveData of paged lists for the UI to observe
    val pagedList: LiveData<PagedList<T>>,
    val loadInitialResultWrapper: LiveData<ResultWrapper<Any>>,
    val loadAfterResultWrapper: LiveData<ResultWrapper<Any>>,
    val refresh: () -> Unit
)