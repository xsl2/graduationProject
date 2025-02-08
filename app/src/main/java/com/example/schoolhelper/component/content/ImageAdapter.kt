package com.example.schoolhelper.component.content

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.QuickViewHolder
import com.example.schoolhelper.R
import com.example.schoolhelper.util.ImageUtil

class ImageAdapter:BaseQuickAdapter<String,QuickViewHolder>() {
    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, data: String?) {
        data?.let {
            val iconView=holder.getView<ImageView>(R.id.icon)
            ImageUtil.show(iconView,it)
        }
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.item_image,parent)
    }
}