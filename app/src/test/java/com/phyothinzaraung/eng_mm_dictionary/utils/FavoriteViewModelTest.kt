package com.phyothinzaraung.eng_mm_dictionary.utils

import com.phyothinzaraung.eng_mm_dictionary.data.Favorite
import com.phyothinzaraung.eng_mm_dictionary.repository.IDictionaryRepository
import com.phyothinzaraung.eng_mm_dictionary.util.DispatcherProvider
import com.phyothinzaraung.eng_mm_dictionary.viewmodel.FavoriteViewModel
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
class FavoriteViewModelTest {

    @Mock
    lateinit var dictionaryRepository: IDictionaryRepository
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        favoriteViewModel = FavoriteViewModel(dictionaryRepository)
        dispatcherProvider = TestDispatcherProvider()
        Dispatchers.setMain(dispatcherProvider.main)
    }

    @Test
    fun testInsertFavorite() = runTest{
        val favorite = Favorite(1, "test", "test")

        favoriteViewModel.insertFavorite(favorite)

        verify(dictionaryRepository).insertFavorite(favorite)
    }

    @Test
    fun testDeleteFavorite() = runTest {
        val favorite = Favorite(1, "test", "test")

        favoriteViewModel.deleteFavorite(favorite)

        verify(dictionaryRepository).deleteFavorite(favorite)
    }

    @Test
    fun testGetFavorite() = runTest {
        val favorites = listOf<Favorite>(Favorite(1, "test", "test"))

        `when`(dictionaryRepository.getFavorites()).thenReturn(flowOf(favorites))
        favoriteViewModel.getFavorites()

        val results = favoriteViewModel.favorites.value
        assertEquals(favorites, results)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}