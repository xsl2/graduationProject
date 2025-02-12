package com.example.schoolhelper.component.articledetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseSingleItemAdapter
import com.example.schoolhelper.R
import com.example.schoolhelper.component.content.Content
import com.example.schoolhelper.databinding.HeaderArticleDetailBinding
import com.example.schoolhelper.util.ImageUtil
import com.example.superui.extension.show
import org.apache.commons.lang3.StringUtils


/**
 * 文章详情头部适配器
 */
class ArticleDetailHeaderAdapter() :
    BaseSingleItemAdapter<Content, ArticleDetailHeaderAdapter.VH>() {

    class VH(val binding: HeaderArticleDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Content) {
            if (StringUtils.isNotBlank(data.title)) {
                binding.title.text = data.title
                binding.title.show()
            }

            binding.content.text = data.content

            binding.commentCount.text =
                binding.content.context.getString(R.string.comments_count, data.commentsCount)

            binding.info.text =
                binding.content.context.getString(R.string.other_count, data.likesCount)

            //用户信息
            ImageUtil.showAvatar(binding.icon, data.user!!.icon)
            binding.nickname.text = data.user!!.nickname

            //归属地
            if (StringUtils.isNotBlank(data.province)) {
                binding.address.show()
                binding.address.text =
                    binding.root.context.getString(R.string.from_address, data.province)
            }

        }
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        return VH(HeaderArticleDetailBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, data: Content?) {
        data?.let {
            holder.bind(it)
        }
    }
}