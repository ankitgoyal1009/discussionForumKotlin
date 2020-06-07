package com.ankitgoyal1009.discussionforum.discussions

import android.content.Context
import android.os.AsyncTask
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import com.ankitgoyal1009.discussionforum.R
import com.ankitgoyal1009.discussionforum.common.Gson
import com.ankitgoyal1009.discussionforum.common.Util
import com.ankitgoyal1009.discussionforum.common.data.DiscussionDatabase
import com.ankitgoyal1009.discussionforum.discussions.data.Discussion
import com.ankitgoyal1009.discussionforum.common.data.DateUtil
import com.ankitgoyal1009.discussionforum.login.LoginRepository
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import java.util.Calendar
import java.util.Date

class DiscussionRepository {
    companion object {
        private lateinit var INSTANCE: DiscussionRepository

        fun getInstance(): DiscussionRepository {
            synchronized(LoginRepository::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = DiscussionRepository()
                }
            }
            return INSTANCE
        }
    }

    fun getDiscussion(context:Context, discussionId: String): LiveData<Discussion> {
        val discussionsDao = DiscussionDatabase.getInstance(context).getDiscussionDao()
        return discussionsDao.getDiscussion(discussionId)
    }

    fun getPublishedDiscussions(context: Context, timestamp: Long?): LiveData<List<Discussion>> {
        val discussionsDao = DiscussionDatabase.getInstance(context).getDiscussionDao()
        return discussionsDao.getAllPublishedDiscussion(timestamp)
    }

    fun getAllDiscussions(context:Context): LiveData<List<Discussion>> {
        val discussionsDao = DiscussionDatabase.getInstance(context).getDiscussionDao()
        return discussionsDao.getAllDiscussion()
    }

    fun initDummyDiscussions(context: Context) {
        object : AsyncTask<Any, Any, Any>() {
            override fun doInBackground(objects: Array<Any>): Any? {
                return parseDummyDiscussionData(context)
            }

        }.execute()
    }

    private fun parseDummyDiscussionData(context: Context) {
        val type = object : TypeToken<List<Discussion>>() {}.getType()
        var list: List<Discussion>?
        try {
            list = Gson.getInstance().fromJson(Util.readJSONFromAsset(context, R.raw.dummy), type)
        } catch (e: JsonParseException) {
            list = emptyList()
        }

        if (list == null) {
            return
        }

        val calendar = Calendar.getInstance()
        for (i in list.indices) {
            val discussion = list[i]
            // Reducing an hour to make sure we get all record immediately after inserting. Ideally we should be taking start of the day
            val l = DateUtil.getStartOfDay(calendar.time.time)
            discussion.date = Date(l)
            calendar.timeInMillis = calendar.timeInMillis + DateUtils.DAY_IN_MILLIS
        }
        val discussionsDao = DiscussionDatabase.getInstance(context).getDiscussionDao()
        discussionsDao.insertAll(list)
    }
}

