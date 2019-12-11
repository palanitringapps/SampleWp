package com.samplewp.ui.activity

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.samplewp.R
import com.samplewp.base.BaseActivity
import com.samplewp.ui.adapter.FeedAdapter
import com.samplewp.viewmodel.FeedsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ViewDataBinding>() {
    private lateinit var feedAdapter: FeedAdapter
    private val viewModel by lazy {
        ViewModelProviders.of(this, application?.let { FeedsViewModel.FeedsViewModelFactory(it) })
            .get(FeedsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.response.observe(this, Observer { setAdapter() })
        sr_pull.setOnRefreshListener {
            sr_pull.isRefreshing = true
            viewModel.refreshFeed()
        }
    }

    override fun getContentView(): Int = R.layout.activity_main

    private fun setAdapter() {
        if (sr_pull.isRefreshing) {
            sr_pull.isRefreshing = false
            feedAdapter.clear()
            feedAdapter.addAll(viewModel.response.value!!)
            return
        }
        supportActionBar?.title = viewModel.response.value?.title
        feedAdapter = FeedAdapter(viewModel.response.value!!)
        rv_feed_list.layoutManager = LinearLayoutManager(this)
        rv_feed_list.adapter = feedAdapter
    }
}