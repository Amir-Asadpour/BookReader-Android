package nl.appical.bookreader.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import nl.appical.bookreader.domain.usecases.GetAllBooksUseCase
import nl.appical.bookreader.domain.usecases.SearchAllBooksUseCase
import nl.appical.bookreader.presentation.models.toUi
import nl.appical.bookreader.presentation.TestModels
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getAllBooksUseCase: GetAllBooksUseCase
    private lateinit var searchAllBooksUseCase: SearchAllBooksUseCase
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        getAllBooksUseCase = mockk()
        searchAllBooksUseCase = mockk()

        Dispatchers.setMain(testDispatcher)
        homeViewModel = HomeViewModel(getAllBooksUseCase, searchAllBooksUseCase)
    }

    @Test
    fun `getBooks sets uiState to Progress and then Content when successful`() = runTest {
        val books = listOf(TestModels.sampleBook)
        coEvery { getAllBooksUseCase() } returns books

        homeViewModel.getBooks()

        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(homeViewModel.uiState.value is HomeUiState.Content)
        val content = homeViewModel.uiState.value as HomeUiState.Content
        assertEquals(books.map { it.toUi() }, content.books)

        coVerify(exactly = 1) { getAllBooksUseCase() }
    }

    @Test
    fun `getBooks sets uiState to TryAgain when exception occurs`() = runTest {
        coEvery { getAllBooksUseCase() } throws RuntimeException()

        homeViewModel.getBooks()

        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(homeViewModel.uiState.value is HomeUiState.TryAgain)

        coVerify(exactly = 1) { getAllBooksUseCase() }
    }

    @Test
    fun `onSearchQueryChanged updates uiState when successful`() = runTest {
        val books = listOf(TestModels.sampleBook)
        coEvery { searchAllBooksUseCase("query") } returns books
        homeViewModel.uiState.value = HomeUiState.Content(books.map { it.toUi() })

        homeViewModel.onSearchQueryChanged("query")

        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(homeViewModel.uiState.value is HomeUiState.Content)
        val contentState = homeViewModel.uiState.value as HomeUiState.Content
        assertEquals(books.map { it.toUi() }, contentState.books)

        coVerify(exactly = 1) { searchAllBooksUseCase("query") }
    }

    @Test
    fun `onSearchQueryChanged does not update uiState if current state is not Content`() = runTest {
        homeViewModel.uiState.value = HomeUiState.Progress

        homeViewModel.onSearchQueryChanged("query")

        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(homeViewModel.uiState.value is HomeUiState.Progress)

        coVerify(exactly = 0) { searchAllBooksUseCase(any()) }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
