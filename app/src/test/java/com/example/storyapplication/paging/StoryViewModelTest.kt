package com.example.storyapplication.paging

import StoryViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.storyapplication.database.StoryRepository
import com.example.storyapplication.responses.ListStoryItem
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import org.junit.Rule
import org.mockito.Mockito

@RunWith(MockitoJUnitRunner::class)
class StoryViewModelTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var storyRepository: StoryRepository
    private lateinit var storyViewModel: StoryViewModel
    private val dummyStories = DataDummy.generateDummyStories()
    @Before
    fun setUp() {
        storyViewModel = StoryViewModel(storyRepository)
    }

    @Test
    fun `when Get Stories Should Not Null and a Valid Data`() {
        val observer = Observer<PagingData<ListStoryItem>> {}

        try {
            val expectedStories = MutableLiveData<PagingData<ListStoryItem>>()
            expectedStories.value = PagingData.from(
                dummyStories
            )
            `when`(storyRepository.getStories()).thenReturn(expectedStories)

            val actualStories = storyViewModel.stories.observeForever(observer)

            Mockito.verify(storyRepository).getStories()
            Assert.assertNotNull(actualStories)
        } finally {
            storyViewModel.stories.removeObserver(observer)
        }
    }
}