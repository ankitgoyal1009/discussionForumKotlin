package com.ankitgoyal1009.discussionforum.discussions.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ankitgoyal1009.discussionforum.R
import com.ankitgoyal1009.discussionforum.discussions.DiscussionViewModel
import com.ankitgoyal1009.discussionforum.discussions.ui.model.Discussion

class DiscussionsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: DiscussionsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewModel: DiscussionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.discussion_list)
        viewManager = LinearLayoutManager(this)
        viewAdapter = DiscussionsAdapter()
        viewModel = ViewModelProvider(this).get(DiscussionViewModel::class.java)
        recyclerView = findViewById<RecyclerView>(R.id.rv_discussions).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        viewModel.getPublishedDiscussions().observe(this, object : Observer<List<Discussion>> {
            override fun onChanged(t: List<Discussion>?) {
                if (t.isNullOrEmpty()) viewModel.initDummyDiscussions()
                viewAdapter.updateAdapter(t)
            }
        })
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, DiscussionsActivity::class.java)
            context.startActivity(intent)
        }
    }
}