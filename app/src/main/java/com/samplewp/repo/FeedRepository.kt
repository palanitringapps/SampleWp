package com.samplewp.repo

import com.samplewp.api.Api
import com.samplewp.base.BaseRepository

class FeedRepository(private val api: Api) : BaseRepository() {
    suspend fun getFeedDeta() = api.getFeeds()
}