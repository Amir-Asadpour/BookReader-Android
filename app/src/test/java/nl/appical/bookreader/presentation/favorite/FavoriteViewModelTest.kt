package nl.appical.bookreader.presentation.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import nl.appical.bookreader.domain.usecases.GetFavoriteBooksUseCase
import nl.appical.bookreader.presentation.TestModels
import nl.appical.bookreader.presentation.models.toUi
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getFavoriteBooksUseCase: GetFavoriteBooksUseCase
    private lateinit var favoriteViewModel: FavoriteViewModel

    private val testSampleBooks = listOf(TestModels.sampleBook, TestModels.sampleBook)

    @Before
    fun setup() {
        getFavoriteBooksUseCase = mockk()

        coEvery { getFavoriteBooksUseCase.invoke() } returns flowOf(testSampleBooks)

        Dispatchers.setMain(testDispatcher)

        favoriteViewModel = FavoriteViewModel(getFavoriteBooksUseCase)
    }

    @Test
    fun `books emits favorite books when use case returns data`() = runTest {
        val result = favoriteViewModel.books.first()
        assertEquals(testSampleBooks.map { it.toUi() }, result)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}