package com.phyothinzaraung.eng_mm_dictionary

import com.phyothinzaraung.eng_mm_dictionary.data.model.Favorite
import com.phyothinzaraung.eng_mm_dictionary.repository.IFavoriteRepository
import com.phyothinzaraung.eng_mm_dictionary.util.DispatcherProvider
import com.phyothinzaraung.eng_mm_dictionary.utils.TestDispatcherProvider
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
    lateinit var repository: IFavoriteRepository
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        dispatcherProvider = TestDispatcherProvider()
        Dispatchers.setMain(dispatcherProvider.io)
        favoriteViewModel = FavoriteViewModel(repository, dispatcherProvider)
    }

    @Test
    fun testInsertFavorite() = runTest {
        val favorite = Favorite(1, "test", "test")

        favoriteViewModel.insertFavorite(favorite)

        verify(repository).insertFavorite(favorite)
    }

    @Test
    fun testDeleteFavorite() = runTest {
        val favorite = Favorite(1, "test", "test")

        favoriteViewModel.deleteFavorite(favorite)

        verify(repository).deleteFavorite(favorite)
    }

    @Test
    fun testGetFavorite() = runTest {
        val favorites = listOf<Favorite>(Favorite(1, "test", "test"))

        `when`(repository.getFavorites()).thenReturn(flowOf(favorites))
        favoriteViewModel.getFavorites()

        val results = favoriteViewModel.favorites.value
        assertEquals(favorites, results)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}