package com.phyothinzaraung.eng_mm_dictionary

import com.phyothinzaraung.eng_mm_dictionary.data.model.Dictionary
import com.phyothinzaraung.eng_mm_dictionary.repository.IDictionaryRepository
import com.phyothinzaraung.eng_mm_dictionary.util.DispatcherProvider
import com.phyothinzaraung.eng_mm_dictionary.utils.TestDispatcherProvider
import com.phyothinzaraung.eng_mm_dictionary.viewmodel.DictionaryViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DictionaryViewModelTest {

    @Mock
    lateinit var dictionaryRepository: IDictionaryRepository
    private lateinit var dictionaryViewModel: DictionaryViewModel
    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        dictionaryViewModel = DictionaryViewModel(
            repository = dictionaryRepository,
        )
        dispatcherProvider = TestDispatcherProvider()
        Dispatchers.setMain(dispatcherProvider.io)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testSearchWords() = runTest {
        val query = "test"
        val testResults = listOf<Dictionary>(
            Dictionary(1, "test", "test", "test", "test", "test")
        )

        `when`(dictionaryRepository.searchWords(query)).thenReturn(flowOf(testResults))
        dictionaryViewModel.searchWords(query)

        val results = dictionaryViewModel.searchResults.value
        assertEquals(testResults, results)
    }

    @Test
    fun testGetDictionaryByStripWord() = runTest {
        val testWord = "test"
        val testDictionary = Dictionary(1, "test", "test", "test", "test", "test")
        `when`(dictionaryRepository.getDictionaryByStripWord(testWord)).thenReturn(
            flowOf(
                testDictionary
            )
        )
        val result = dictionaryViewModel.getDictionaryByStripWord(testWord).first()
        assertEquals(testDictionary, result)

    }
}