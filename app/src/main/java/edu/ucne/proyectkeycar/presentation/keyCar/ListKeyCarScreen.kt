package edu.ucne.proyectkeycar.presentation.keyCar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.proyectkeycar.data.entities.KeyCarEntity
import edu.ucne.proyectkeycar.data.remote.dto.KeyCarDto

@Composable
fun ListKeyCarScreen(
    goToAdd: () -> Unit,
    onSelect: (Int) -> Unit,
    viewModel: KeyCarViewModel = hiltViewModel()
) {
    val uiState = viewModel.uistate.collectAsStateWithLifecycle()
    ListKeyCarBodyScreen(
        uiState = uiState.value,
        goToAdd = goToAdd,
        onSelect = onSelect
    )
}

@Composable
fun ListKeyCarBodyScreen(
    uiState: Uistate,
    goToAdd: () -> Unit,
    onSelect: (Int) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize() // Contenedor principal
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Lista llaves", modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
            ) {

                Text(
                    text = "nombre",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "costo",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "precio",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "iva",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "tipo de llave",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.keyCars) {
                    KeyCarRow(it, onSelect)
                }
            }
        }

        FloatingActionButton(
            onClick = goToAdd,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(top = 10.dp, end = 20.dp, bottom = 35.dp)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Agregar Venta")
        }
    }
}

@Composable
fun KeyCarRow(it: KeyCarEntity, onSelect: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onSelect(it.keyId ?: 0) }
            .fillMaxSize()
    ) {

        Text(
            text = it.nombre ?: "",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = it.costo.toString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = it.precio.toString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = it.iva.toString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = it.keyTypeId.toString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
    }
}