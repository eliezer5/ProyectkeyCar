package edu.ucne.proyectkeycar.presentation.keyType

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AddKeyTypeScreen(
    goToBack: () -> Unit,
    viewModel: KeyTypeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uistate.collectAsStateWithLifecycle()
    AddKeyTypeBodyScreen(uiState = uiState.value, goToBack = goToBack, onEvent = { event -> viewModel.onEvent(event) })
}

@Composable
fun AddKeyTypeBodyScreen(
    uiState: Uistate,
    goToBack: () -> Unit,
    onEvent: (KeyTypeEvent) -> Unit
) {
    Box(
    ) {
        Column(
            modifier = Modifier

                .fillMaxSize()
        ) {
            Text(text = "Agregar tipo de llave", modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                label = { Text(text = "Tipo de llave") },
                value = uiState.tipoLlave ?: "",
                onValueChange = { onEvent(KeyTypeEvent.OnchangeTypeKey(it)) },
                modifier = Modifier.fillMaxWidth()
            )
            uiState.errorTipoLlave.let { error ->
                Text(text = error, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(onClick = {
                    onEvent(KeyTypeEvent.Save)
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Guardar")
                    Text(text = "Guardar")
                }
            }
        }
        FloatingActionButton(
            onClick = goToBack, modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(top = 10.dp, end = 20.dp, bottom = 35.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver"
            )
        }

    }
}