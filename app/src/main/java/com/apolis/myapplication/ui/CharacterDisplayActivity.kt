package com.apolis.myapplication.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolis.myapplication.R
import com.apolis.myapplication.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

//Create a sample application to show the list of Characters from the show Rick and Morty.
//API Doc: https://rickandmortyapi.com/documentation/#get-all-characters
//The application:
//• Should fetch the list of characters (https://rickandmortyapi.com/api/character/)
//• Should show a scrollable list of those characters including Name, Status, Species and an image of the character
//• When a character is clicked, it should show a popup or dialog stating the character’s location Name (location.name)
//Plus Points:
//• Can work both on Landscape (Grid View) and Portrait (Vertical List)
//• The list of characters consists of several pages; can implement fetching additional pages of characters as the User scrolls
//NOTE:
//• The code must be shared in GitHub or BitBucket, with frequent commits.
class CharacterDisplayActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var charViewModel: CharacterViewModel
    private val adapterCharacters = AdapterCharacters(this)
    private val repoCharacters = RepoChars()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        init()
        charactersObserved()
    }


    /**
     * initialize view model and call to load data from api
     * initiliaze recycler view providing adapter and initial layout manager
     */
    private fun init() {
       // charViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        charViewModel =
            ViewModelProvider(this, CharacterViewModelFactory(repoCharacters)).get(
                CharacterViewModel::class.java
            )
        charViewModel.loadCharacters()
        recycler_view.adapter = adapterCharacters
        recycler_view.layoutManager = LinearLayoutManager(this)
    }


    /**
     * observer for the live data from view model
     */

    private fun charactersObserved() {
        charViewModel.callCharObserver().observe(
            this, Observer {
                if (it != null) {
                    adapterCharacters.setCharacterList(it)
                }
            }
        )
    }

    /**
     * provide correct layout manager depending on the configuration
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recycler_view.layoutManager = GridLayoutManager(this, 2)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recycler_view.layoutManager = LinearLayoutManager(this)
        }
    }

}