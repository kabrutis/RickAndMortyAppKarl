package com.apolis.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.apolis.myapplication.model.Location
import com.apolis.myapplication.model.Origin
import com.apolis.myapplication.ui.CharacterViewModel
import com.apolis.myapplication.ui.RepoChars
import com.apolis.myapplication.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterViewModelTest {
    private lateinit var characterViewModel: CharacterViewModel
    lateinit var characterRepo: RepoChars

    /**
     * configure to made live data run synchronously
     */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testCoroutineThread = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineThread)
        characterRepo = Mockito.mock(RepoChars::class.java)
        characterViewModel = CharacterViewModel(characterRepo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun checkRepositoryData() {
        runBlocking {
            Mockito.`when`(characterRepo.getRMCharacters())
                .thenReturn(providesTestList())
            characterViewModel.loadCharacters()
            val testList = characterViewModel.callCharObserver().value
            assert(testList!!.size == 5)
            assert(testList[0].created == "Created0")
            assert(testList[1].gender == "gender1")
            assert(testList[2].image == "image2")
            assert(testList[3].location.name == "location3")
            assert(testList[3].location.url == "url3")
            assert(testList[4].status == "status4")
            assert(testList[4].type == "type4")
        }

    }

    private fun providesTestList(): List<Result> {
        val list = ArrayList<Result>()
        for (i in 0..4) {
            list.add(
                Result(
                    created = "Created$i",
                    episode = listOf("$i"),
                    gender = "gender$i",
                    id = i,
                    image = "image$i",
                    location = Location("location$i", "url$i"),
                    name = "name$i",
                    origin = Origin("name$i", "url$i"),
                    species = "species$i",
                    status = "status$i",
                    type = "type$i",
                    url = "url$i"
                )
            )
        }
        return list
    }

}