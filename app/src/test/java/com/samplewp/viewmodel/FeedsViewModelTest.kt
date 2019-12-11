package com.samplewp.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.samplewp.model.Row
import com.samplewp.model.SampleResponse
import mock
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import java.util.*
import kotlin.collections.ArrayList

class FeedsViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: FeedsViewModel
    private val observer: Observer<SampleResponse> = mock()
    private val app: Application = mock()

    @Before
    fun before() {

        viewModel = FeedsViewModel(app)
        viewModel.response.observeForever(observer)
    }

    @Test
    fun fetchFeeds() {
        val sample = ArrayList<Row>()
        sample.add(Row("sample", "sample", "sample"))
        val expectedData = SampleResponse(ArrayList(), "sample")
        viewModel.refreshFeed()

        val captor = ArgumentCaptor.forClass(SampleResponse::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals(expectedData, value)
        }
    }
}