package com.compose.mikasa.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.mikasa.model.CharactersModel
import com.compose.mikasa.repository.MiKasaRepository
import com.compose.mikasa.utils.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MiKasaViewModel @Inject constructor(
    private val miKasaRepository: MiKasaRepository
) : ViewModel() {

    private val _characters: MutableStateFlow<ResourceState<CharactersModel>> =
        MutableStateFlow(ResourceState.Loading())
    val characters: StateFlow<ResourceState<CharactersModel>> = _characters
    init {
        getCharactersTitans()
    }
    private fun getCharactersTitans() {
        viewModelScope.launch(Dispatchers.IO) {
            miKasaRepository.getCharactersTitans()
                .collectLatest { characterResponse ->
                    Log.d(TAG, "getCharactersTitans: $characterResponse")
                    _characters.value = characterResponse
                }
        }
    }

    companion object {
        private val TAG = "MiKasaViewModel"
    }
}