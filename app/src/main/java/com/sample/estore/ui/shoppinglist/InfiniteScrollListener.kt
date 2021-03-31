package com.sample.estore.ui.shoppinglist

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class InfiniteScrollListener(private val layoutManager: GridLayoutManager) :
    RecyclerView.OnScrollListener() {
    private var visibleThreshold = 5

    private var currentPage = 0

    private var previousTotalItemCount = 0

    private var loading = true

    private val startingPageIndex = 0

    init {
        visibleThreshold *= layoutManager.spanCount
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        var lastVisibleItemPosition = 0
        val totalItemCount = layoutManager.itemCount
        lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

        if (totalItemCount < previousTotalItemCount) {
            currentPage = startingPageIndex
            previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                loading = true
            }
        }

        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            currentPage++
            onLoadMore(currentPage, totalItemCount, recyclerView)
            loading = true
        }
    }

    fun resetState() {
        currentPage = startingPageIndex
        previousTotalItemCount = 0
        loading = true
    }

    // Loads more data based on the page
    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?)
}