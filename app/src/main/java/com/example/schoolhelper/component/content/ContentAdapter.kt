package com.example.schoolhelper.component.content

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.schoolhelper.R
import com.example.schoolhelper.databinding.ItemContentBinding
import com.example.schoolhelper.util.ImageUtil
import com.example.super_k.util.StringUtil
import com.example.superui.date.SuperDateUtil
import com.example.superui.decoration.GridDividerItemDecoration
import com.example.superui.util.DensityUtil
import org.apache.commons.collections4.CollectionUtils
import org.apache.commons.lang3.StringUtils

/**
 * 内容适配器
 */
class ContentAdapter(val viewModel: ContentViewModel) : BaseQuickAdapter<Content, ContentAdapter.ViewHolder>() {
    /**
     * 绑定数据
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int, data: Content?) {
        holder.bindData(data!!)
        holder.itemView.setOnClickListener {
            viewModel.itemClick(data)
        }
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(ItemContentBinding.inflate(LayoutInflater.from(context),parent,false))
        //在activity和fragment中可以直接用LayoutInflater，在Adapter中需要LayoutInflater.from(context)，没有LayoutInflater的实例

    }
    inner class ViewHolder(val binding:ItemContentBinding):RecyclerView.ViewHolder(binding.root) {
        /**
         * 显示数据
         */
        fun bindData(data: Content) {
            if (StringUtils.isNotBlank(data.title)) {
                binding.content.text = data.title
            } else {
                binding.content.text = data.content
            }

            binding.nickname.text = data.user!!.nickname
            binding.commentsCount.text =
                binding.commentsCount.context.getString(R.string.comments_count, data.commentsCount)

            binding.date.text = SuperDateUtil.commonFormat(data.createdAt)
//
            binding.videoContainer.visibility = View.GONE
            binding.list.visibility = View.GONE

            if (StringUtils.isNotBlank(data.uri)) {
                //视频
                binding.videoContainer.visibility = View.VISIBLE

                ImageUtil.show(binding.icon, data.icons?.get(0))

                //视频时长
                binding.duration.text = SuperDateUtil.s2ms(data.duration)
            }
            else if (CollectionUtils.isNotEmpty(data.icons)) {
                //有图片

                //显示图片列表控件
                binding.list.visibility = View.VISIBLE

                //动态计算显示列数
                //动态计算显示列数
                var spanCount = 2
                if (data.icons!!.size >= 3) {
                    //大于等于3张图片

                    //显示3列
                    spanCount = 3
                }

                //设置布局管理器
                val layoutManager = GridLayoutManager(binding.list.context, spanCount)
                binding.list.layoutManager = layoutManager

                //分割线
                if (binding.list.itemDecorationCount > 0) {
                    binding.list.removeItemDecorationAt(0)
                }
                val itemDecoration = GridDividerItemDecoration(
                    binding.list.context,
                    DensityUtil.dip2px(binding.list.context, 5f).toInt()
                )
                binding.list.addItemDecoration(itemDecoration)

                //设置适配器
                var adapter = ImageAdapter()
                adapter.setOnItemClickListener { adapter, view, position ->
                    viewModel.previewMedias(binding.list, data.icons!!, position)
                }

                binding.list.adapter = adapter
                adapter.submitList(data.icons!!)
            }
        }
    }
}