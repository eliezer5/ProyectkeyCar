package edu.ucne.proyectkeycar.presentation.keyType

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.ucne.proyectkeycar.Repository.KeyTypeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class KeyTypeViewModel @Inject constructor(
    private val keyTypeRepository: KeyTypeRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(Uistate())
    val uistate = _uiState.asStateFlow()

    init {
        getKeyTypes()
    }

    private fun getKeyTypes(){
        viewModelScope.launch {
            val result = keyTypeRepository.getKeyTypes()

            _uiState.update {
                it.copy(
                    keyTypes = result.data?: emptyList()
                )
            }
        }
    }

    private fun save() {
        viewModelScope.launch {
            keyTypeRepository.addKeyType(uistate.value.toEntity())
            nuevo()
        }
    }

    private fun onChangeTipoLlave(tipo: String){
        _uiState.update {
            it.copy(
                tipoLlave = tipo
            )
        }
    }

    private fun nuevo() {
        _uiState.update {
            it.copy(
                keyTypeId = null,
                tipoLlave = "",
                errorTipoLlave = ""
            )
        }
    }
}