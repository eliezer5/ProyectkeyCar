package edu.ucne.proyectkeycar.presentation.keyCar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.proyectkeycar.Repository.keyCarRepository
import edu.ucne.proyectkeycar.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeyCarViewModel @Inject constructor(
    private val keyCarRepository: keyCarRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(Uistate())
    val uistate = _uiState.asStateFlow()

    init {
        getKeyCars()
    }

    private fun getKeyCars(){
        viewModelScope.launch {
            keyCarRepository.getKeyCars().collect{ result ->
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
                                keyCars = result.data ?: emptyList()
                            )
                        }
                    }
                }
            }
        }
    }

    private fun save(){
        viewModelScope.launch {
            val result = keyCarRepository.AddKeyCar(uistate.value.toEntity())
            result.collect{ resource ->
                when(resource){
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                error = resource.message ?: "Error"
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
                               error = "KeyCar agregado",
                            )
                        }
                        nuevo()
                    }
                }
            }


        }
    }
    private fun OnchangeNombre(nombre: String){
        _uiState.update {
            it.copy(
                nombre = nombre
            )
        }
    }

    private fun OnchangeCosto(costo: String){
        val NewCosto = costo.toDoubleOrNull()
        _uiState.update {
            it.copy(
                costo = NewCosto
            )
        }
    }

    private fun OnchangePrecio(precio: String){
        val NewPrecio = precio.toIntOrNull()
        _uiState.update {
            it.copy(
                precio = NewPrecio
            )
        }
    }

    private fun OnchangeIva(iva: String) {
        val NewIva = iva.toIntOrNull()
        _uiState.update {
            it.copy(
                iva = NewIva
            )
        }
    }

    private fun OnchangeKeyTypeId(keyTypeId: Int){
        _uiState.update {
            it.copy(
                keyTypeId = keyTypeId
            )
        }
    }

    private fun nuevo(){
        _uiState.update {
            it.copy(
                keyId = null,
                nombre = "",
                keyTypeId = null,
                costo = null,
                precio = null,
                iva = null,
                errorNombre = "",
                errorCosto = "",
                errorPrecio = "",
                errorIva = ""
            )
        }
    }

    fun OnEvent(event: KeyCarEvent){
        when(event){
            is KeyCarEvent.OnchangeNombre -> OnchangeNombre(event.nombre)
            is KeyCarEvent.OnchangeCosto -> OnchangeCosto(event.costo)
            is KeyCarEvent.OnchangePrecio -> OnchangePrecio(event.precio)
            is KeyCarEvent.OnchangeIva -> OnchangeIva(event.iva)
            is KeyCarEvent.OnchangeKeyTypeId -> OnchangeKeyTypeId(event.keyTypeId)
            is KeyCarEvent.Save -> save()
        }
    }

}