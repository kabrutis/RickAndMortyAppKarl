package com.apolis.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.apolis.myapplication.model.Result
import kotlinx.coroutines.launch

class CharacterViewModel(private val repoCharacters : RepoChars) : ViewModel() {
    private val charactersObserved: MutableLiveData<List<Result>> by lazy {
        MutableLiveData<List<Result>>()
    }

    fun callCharObserver() = charactersObserved

    /**
     * within scope of view model, coroutine launches calling repo fun to retrieve
     */

    fun loadCharacters() {
        viewModelScope.launch {
            val charactersResponse = repoCharacters.getRMCharacters()
            charactersObserved.postValue(charactersResponse)
        }
    }
}
class CharacterViewModelFactory(private val repo: RepoChars) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharacterViewModel(repo) as T
    }

}