package com.sample.estore.ui.shoppinglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.estore.BR
import com.sample.estore.R
import com.sample.estore.databinding.ViewStoreItemBinding
import com.sample.estore.databinding.ViewStoreItemBindingImpl
import com.sample.estore.domain.model.StoreItem
import com.sample.estore.ui.providers.ImageResourceLoader

class StoreAdapter(
    private var storeItems: List<StoreItem>,
    private val imageResourceLoader: ImageResourceLoader
) :
    RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewStoreItemBinding = DataBindingUtil.inflate(
            layoutInflater, viewType, parent, false
        )
        return StoreViewHolder(binding, imageResourceLoader)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.view_store_item
    }

    override fun onBindViewHolder(viewHolder: StoreViewHolder, position: Int) {
        viewHolder.bind(storeItems[position])
    }

    fun setItems(storeItems: List<StoreItem>) {
        this.storeItems = storeItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = storeItems.count()

    class StoreViewHolder(
        private val binding: ViewStoreItemBinding,
        private val imageResourceLoader: ImageResourceLoader
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StoreItem?) {
            imageResourceLoader.loadImageIntoView(item?.imageUrl ?: "", binding.itemImageView)
            binding.setVariable(BR.model, item)
            binding.executePendingBindings()
        }
    }
}