package com.ankitgoyal1009.discussionforum.discussions.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ankitgoyal1009.discussionforum.R
import com.ankitgoyal1009.discussionforum.discussions.ui.DiscussionsAdapter.DiscussionViewHolder
import com.ankitgoyal1009.discussionforum.discussions.ui.model.Discussion


class DiscussionsAdapter : RecyclerView.Adapter<DiscussionViewHolder>() {
    var discussions: List<Discussion>? = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscussionViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.discussion_row, parent, false)
        return DiscussionViewHolder(layout)

    }

    override fun getItemCount(): Int {
        return discussions?.size ?: 0
    }

    override fun onBindViewHolder(holder: DiscussionViewHolder, position: Int) {
        val discussion = discussions?.get(position)
        holder.title.text = discussion?.title
        holder.date.text = discussion?.date
    }

    fun updateAdapter(discussions: List<Discussion>?) {
        this.discussions = discussions
    }

    class DiscussionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById(R.id.tv_discussion) as TextView
        val date = view.findViewById(R.id.tv_discussion_date) as TextView
    }
}