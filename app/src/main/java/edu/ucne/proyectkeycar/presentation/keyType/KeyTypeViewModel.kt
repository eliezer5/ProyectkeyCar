package edu.ucne.proyectkeycar.presentation.keyType

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.proyectkeycar.Repository.KeyTypeRepository
import edu.ucne.proyectkeycar.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeyTypeViewModel @Inject constructor(
    private val keyTypeRepository: KeyTypeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(Uistate())
    val uistate = _uiState.asStateFlow()

    init {
        getKeyTypes()
    }

    private fun getKeyTypes() {
        viewModelScope.launch {
            keyTypeRepository.getKeyTypes().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                error = result.message ?: "Error"
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                keyTypes = result.data ?: emptyList()
                            )
                        }
                    }

                }
            }
        }
    }

    private fun save() {
        viewModelScope.launch {
            if(validar()) {
                keyTypeRepository.addKeyType(uistate.value.toEntity()).collect { result ->
                    nuevo()
                    when(result){
                        is Resource.Error -> {
                            _uiState.update {
                                it.copy(
                                    error = result.message ?: "Error"
                                )
                            }
                        }
                        is Resource.Loading -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = true
                                )
                            }
                        }
                        is Resource.Success -> {
                            _uiState.update {
                                it.copy(
                                    error = "Key Type agregado",
                                )

                            }

                        }
                    }

                }

            }
        }
    }

    private fun onChangeTipoLlave(tipo: String) {
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

    private fun validar(): Boolean {
        var error = false
        _uiState.update {
            it.copy(
                errorTipoLlave = if(it.errorTipoLlave.isBlank()) {
                    error = true
                    "Tipo de llave no puede estar vacio"
                } else ""
            )
        }

        return !error
    }

    fun onEvent(event: KeyTypeEvent) {
        when (event) {
            is KeyTypeEvent.OnchangeTypeKey -> onChangeTipoLlave(event.typeKey)
            is KeyTypeEvent.Save -> save()
        }
    }
}