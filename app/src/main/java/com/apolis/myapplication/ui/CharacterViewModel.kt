package com.apolis.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apolis.myapplication.model.Result
import kotlinx.coroutines.launch

class CharacterViewModel:ViewModel() {
    private val repoCharacters = RepoChars()
    private val charactersObserved: MutableLiveData<List<Result>> by lazy{
        MutableLiveData<List<Result>>()
    }
    fun callCharObserver() = charactersObserved

    fun loadCharacters(){
        viewModelScope.launch {
            val charactersResponse = repoCharacters.getRMCharacters()
            charactersObserved.postValue(charactersResponse)
        }
    }
}