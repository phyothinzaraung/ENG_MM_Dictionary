package com.phyothinzaraung.eng_mm_dictionary.utils

import com.phyothinzaraung.eng_mm_dictionary.data.Recent
import com.phyothinzaraung.eng_mm_dictionary.repository.IDictionaryRepository
import com.phyothinzaraung.eng_mm_dictionary.util.DispatcherProvider
import com.phyothinzaraung.eng_mm_dictionary.viewmodel.RecentViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RecentViewModelTest {

    @Mock
    lateinit var dictionaryRepository: IDictionaryRepository
    private lateinit var recentViewModel: RecentViewModel
    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        recentViewModel = RecentViewModel(dictionaryRepository)
        dispatcherProvider = TestDispatcherProvider()
        Dispatchers.setMain(dispatcherProvider.main)
    }

    @Test
    fun testInsertRecent() = runTest {
        val recent = Recent(1, "test", "test")
        recentViewModel.insertRecent(recent)
        verify(dictionaryRepository).insertRecent(recent = recent)
    }

    @Test
    fun testClearAllRecent() = runTest {
        recentViewModel.clearAllRecent()
        verify(dictionaryRepository).clearAllRecent()
    }

    @Test
    fun testGetRecents() = runTest {
        val recents = listOf<Recent>(Recent(1, "test", "test"))

        `when`(dictionaryRepository.getRecent()).thenReturn(flowOf(recents))
        recentViewModel.getRecent()

        val results = recentViewModel.recent.value
        assertEquals(recents, results)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}