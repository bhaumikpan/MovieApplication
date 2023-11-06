package com.example.movieapplication

import com.example.movieapplication.data.Movie
import com.example.movieapplication.di.ServerResponse
import com.example.movieapplication.domain.MovieRepo
import com.example.movieapplication.domain.UseCaseRepo
import com.example.movieapplication.domain.UseCaseRepoImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class RepoTest {

    private val useCase: UseCaseRepo = mockk(relaxed = true)

    private val api: MovieRepo = mockk(relaxed = true)

    private val useCaseImpl = UseCaseRepoImpl(api)

    @Test
    fun `test success call`() = runTest {
        val movies = mutableListOf<Movie>()
        movies.add(Movie(false, "Test_backdrop_1", 101, "", "", "", "", "", "", 1.0))
        val dummyServerResponse = ServerResponse(1, movies)
        coEvery {
            api.getMoviesList()
        } returns dummyServerResponse

        val displayModelList = mutableListOf<Movie>()
        displayModelList.add(Movie(false, "Test_backdrop_2", 101, "", "", "", "", "", "", 1.0))

        coEvery {
            useCase.getMovieListToDisplay()
        } returns displayModelList

        val answer = useCaseImpl.getMovieListToDisplay()

        assert(answer.size == 1)
        assert(answer[0].backdropPath == "Test_backdrop_1")
    }
}