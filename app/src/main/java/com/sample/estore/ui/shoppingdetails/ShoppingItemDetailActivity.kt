package com.sample.estore.ui.shoppingdetails

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sample.estore.R
import com.sample.estore.databinding.ActivityShoppingDetailBinding
import com.sample.estore.domain.model.StoreItem
import com.sample.estore.ui.providers.ImageResourceLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_shopping_detail.*
import javax.inject.Inject

@AndroidEntryPoint
class ShoppingItemDetailActivity : AppCompatActivity() {

    companion object {
        const val MODEL = "model"
    }

    @Inject
    lateinit var imageResourceLoader: ImageResourceLoader

    private val viewModel: ShoppingItemDetailViewModel by viewModels()

    private lateinit var binding: ActivityShoppingDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_detail)
        val storeItem: StoreItem? = intent?.getParcelableExtra(MODEL)
        binding.model = storeItem
        storeItem?.imageUrl?.let {
            imageResourceLoader.loadImageIntoView(it, binding.heroImageView)
        }
        binding.priceTextView.paintFlags = priceTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        setToolbar()
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}