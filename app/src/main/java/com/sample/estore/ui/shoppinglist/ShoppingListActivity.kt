package com.sample.estore.ui.shoppinglist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.estore.R
import com.sample.estore.databinding.ActivityShoppingListBinding
import com.sample.estore.ui.providers.ImageResourceLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShoppingListActivity : AppCompatActivity() {

    companion object {
        private const val ITEMS_PER_ROW = 2
        private const val STARTING_PAGE_ID = 1
        private const val PAGE_COUNT = 30
    }

    private val viewModel: ShoppingListViewModel by viewModels()

    @Inject
    lateinit var imageResourceLoader: ImageResourceLoader

    private lateinit var binding: ActivityShoppingListBinding

    private var storeAdapter: StoreAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_list)
        setupRecyclerView()
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.storeData.observe(this, {
            storeAdapter?.setItems(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchShoppingList(STARTING_PAGE_ID, PAGE_COUNT)
    }

    private fun setupRecyclerView() {
        binding.shoppingListRecyclerView.run {
            val itemDecoration = GridItemDecoration(
                resources.getDimension(R.dimen.grid_spacing).toInt()
            )
            addItemDecoration(itemDecoration)
            layoutManager = GridLayoutManager(this.context, ITEMS_PER_ROW)
            storeAdapter = StoreAdapter(mutableListOf(), imageResourceLoader)
            adapter = storeAdapter
            addOnScrollListener(object :
                InfiniteScrollListener(layoutManager as GridLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    viewModel.fetchShoppingList(page, totalItemsCount)
                }
            })
        }
    }
}