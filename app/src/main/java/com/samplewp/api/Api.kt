package com.samplewp.api

import com.samplewp.model.SampleResponse
import retrofit2.http.GET

interface Api {
    @GET("s/2iodh4vg0eortkl/facts.json")
    suspend fun getFeeds(): SampleResponse
}