package com.example.schoolhelper.component.articledetail

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.QuickAdapterHelper
import com.example.schoolhelper.R
import com.example.schoolhelper.activity.BaseViewModelActivity
import com.example.schoolhelper.component.content.Content
import com.example.schoolhelper.component.userdetail.UserDetailActivity
import com.example.schoolhelper.databinding.ActivityArticleDetailBinding
import com.example.schoolhelper.util.Constant
import com.example.schoolhelper.util.ImageUtil
import kotlinx.coroutines.launch

/**
 * 文章详情界面
 */
class ArticleDetailActivity : BaseViewModelActivity<ActivityArticleDetailBinding>() {
    private lateinit var articleDetailHeaderAdapter: ArticleDetailHeaderAdapter
    private lateinit var helper: QuickAdapterHelper
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: CommentAdapter
    private lateinit var viewModel: ArticleDetailViewModel
    override fun initViews() {
        super.initViews()
        //对于一个recycleview来说必须要设置一个layoutmanager
        layoutManager = LinearLayoutManager(this)
        binding.list.layoutManager = layoutManager
    }

    override fun initDatum() {
        super.initDatum()
        //ViewModel 的默认创建方式是无参数的，如果你需要传递参数（例如在 ViewModel 中进行网络请求或数据库操作时需要的参数），则必须自定义 ViewModelProvider.Factory 来提供这种功能。通过 Factory，你可以灵活地将任何类型的参数传递给 ViewModel，并避免直接修改 ViewModel 的构造方法。
        val viewModelFactory =
            ArticleDetailViewModelFactory(intent.getStringExtra(Constant.ID)!!)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ArticleDetailViewModel::class.java)
        initViewModel(viewModel)
        //适配器
        adapter = CommentAdapter(viewModel)
        helper = QuickAdapterHelper.Builder(adapter)
            .build()

        //头部
        articleDetailHeaderAdapter = ArticleDetailHeaderAdapter().apply {
            //item子控件点击
            addOnItemChildClickListener(R.id.user_container) { adapter, _, position ->
                startActivityExtraId(UserDetailActivity::class.java, viewModel.content.user!!.id!!)
            }

            //item点击
            setOnItemClickListener { _, _, _ ->

            }
        }
        helper.addBeforeAdapter(articleDetailHeaderAdapter)

        binding.list.adapter = helper.adapter


        viewModel.loadData()

        lifecycleScope.launch {
            viewModel.data.collect {
                showData(it)
            }
        }
        //评论
        lifecycleScope.launch {
            viewModel.comments
                .collect { data ->
                    if (data.page == 1) {
                        adapter.submitList(data.data)
                    } else {
                        adapter.addAll(data.data!!)
                    }
                }
        }

    }

    private fun showData(data: Content) {
        articleDetailHeaderAdapter.setItem(data, null)

        //用户信息
        ImageUtil.showAvatar(binding.icon, data.user!!.icon)
        binding.nickname.text = data.user!!.nickname

        if (data.isLike()) {
            binding.like.setImageResource(R.drawable.thumb_selected)
        } else {
            binding.like.setImageResource(R.drawable.baseline_thumb)
        }
    }

    override fun initListeners() {
        super.initListeners()
        //返回按钮点击
        binding.back.setOnClickListener { finish() }

        //监听列表滚动
        binding.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // 获取第一个可见的item的position
                val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()

                // 获取RecyclerView的滚动距离
                val scrollY = recyclerView.computeVerticalScrollOffset()

                Log.d("TAG", "onScrolled: $scrollY")

                binding.userContainer.visibility =
                    if (scrollY >= 350) View.VISIBLE else View.INVISIBLE
            }
        })

    }

    companion object {
        const val TAG = "ArticleDetailActivity"
    }
}