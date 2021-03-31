package com.sample.estore.ui.shoppinglist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.estore.R
import com.sample.estore.databinding.ActivityShoppingListBinding

class ShoppingListActivity : AppCompatActivity() {

    companion object {
        private const val ITEMS_PER_ROW = 2
    }

    private val viewModel: ShoppingListViewModel by viewModels()

    private lateinit var binding: ActivityShoppingListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_list)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.shoppingListRecyclerView.run {
            val itemDecoration = GridItemDecoration(
                resources.getDimension(R.dimen.grid_spacing).toInt()
            )
            addItemDecoration(itemDecoration)
            layoutManager = GridLayoutManager(this.context, ITEMS_PER_ROW)
            addOnScrollListener(object :
                InfiniteScrollListener(layoutManager as GridLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    viewModel.fetchShoppingList(page, totalItemsCount)
                }
            })
        }
    }
}