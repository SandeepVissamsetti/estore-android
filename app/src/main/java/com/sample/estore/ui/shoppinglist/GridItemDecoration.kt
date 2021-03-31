package com.sample.estore.ui.shoppinglist

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration(private val verticalSpace: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.set(verticalSpace, verticalSpace, verticalSpace, verticalSpace)
    }
}