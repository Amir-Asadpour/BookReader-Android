package nl.appical.bookreader.presentation.bookdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import nl.appical.bookreader.domain.models.Book
import nl.appical.bookreader.domain.usecases.GetBookUseCase
import nl.appical.bookreader.domain.usecases.ToggleBookFavoriteUseCase
import nl.appical.bookreader.presentation.TestModels
import nl.appical.bookreader.presentation.models.toUi
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getBookUseCase: GetBookUseCase
    private lateinit var toggleBookFavoriteUseCase: ToggleBookFavoriteUseCase
    private lateinit var bookDetailViewModel: BookDetailViewModel

    private val bookId = "1"

    private val book = TestModels.sampleBook.copy(id = bookId)

    @Before
    fun setup() {
        getBookUseCase = mockk()
        toggleBookFavoriteUseCase = mockk()

        coEvery { getBookUseCase(bookId) } returns flowOf(book)

        Dispatchers.setMain(testDispatcher)

        bookDetailViewModel = BookDetailViewModel(
            bookId = bookId,
            getBookUseCase = getBookUseCase,
            toggleBookFavoriteUseCase = toggleBookFavoriteUseCase
        )
    }

    @Test
    fun `book emits book details when use case returns data`() = runTest {
        val result = bookDetailViewModel.book.first()

        assertEquals(book.toUi(), result)
    }

    @Test
    fun `toggleBookFavorite invokes use case with the latest book`() = runTest {
        coEvery { toggleBookFavoriteUseCase.invoke(any()) } returns Unit

        // Trigger flow production to set the latest book internally in viewModel
        bookDetailViewModel.book.first()

        bookDetailViewModel.toggleBookFavorite()

        testDispatcher.scheduler.advanceUntilIdle()

        val capturedBook = slot<Book>()

        coVerify { toggleBookFavoriteUseCase.invoke(capture(capturedBook)) }
        assertEquals(book, capturedBook.captured)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
