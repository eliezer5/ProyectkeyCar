package edu.ucne.proyectkeycar.presentation

import kotlinx.serialization.Serializable

sealed class Screen {

    @Serializable
    data object KeyCarList: Screen()

    @Serializable
    data class KeyCar(val keyCarId: Int): Screen()

    @Serializable
    data object KeyTypeList: Screen()
    @Serializable
    data class keyType(val KeyTypeId: Int): Screen()

}