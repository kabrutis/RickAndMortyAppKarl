package com.apolis.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var charViewModel: CharacterViewModel
    private val adapterCharacters = AdapterCharacters(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        charViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        charViewModel.loadCharacters()
        init()
        charactersObserved()
    }

    private fun init() {
        recycler_view.adapter = adapterCharacters
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

    private fun charactersObserved() {
        charViewModel.callCharObserver().observe(
            this, Observer {
                if (it != null) {
                    adapterCharacters.setCharacterList(it)
                }
            }
        )
    }

}