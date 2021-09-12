package com.ankitgoyal1009.discussionforum.discussions

import android.app.Application
import android.text.format.DateUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.ankitgoyal1009.discussionforum.common.data.DateUtil
import com.ankitgoyal1009.discussionforum.discussions.ui.model.Discussion
import kotlinx.coroutines.launch

class DiscussionViewModel(private val mApplication: Application) : AndroidViewModel(mApplication) {
    private var mRepository: DiscussionRepository = DiscussionRepository.getInstance()

    fun getPublishedDiscussions(): LiveData<List<Discussion>> {
        val discussions =
            mRepository.getPublishedDiscussions(mApplication, System.currentTimeMillis())

        return Transformations.map(discussions) {
            transformIntoDiscussionViewModel(it)
        }
    }

    private fun transformIntoDiscussionViewModel(it: List<com.ankitgoyal1009.discussionforum.discussions.data.Discussion>): List<Discussion> {
        val transformedList = mutableListOf<Discussion>()
        for (discussion in it) {
            transformedList.add(
                Discussion(
                    discussion.title,
                    DateUtil.dateToString(discussion.date),
                    DateUtils.isToday(discussion.date?.time ?: 0)
                )
            )
        }
        return transformedList.toList()
    }

    fun initDummyDiscussions() {
        viewModelScope.launch { mRepository.initDummyDiscussions(mApplication) }
    }
}
