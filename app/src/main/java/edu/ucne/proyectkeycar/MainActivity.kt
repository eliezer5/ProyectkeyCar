package edu.ucne.proyectkeycar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.proyectkeycar.presentation.navegation.NavHostKeyCar
import edu.ucne.proyectkeycar.ui.theme.ProyectkeyCarTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectkeyCarTheme {
                val navHost = rememberNavController()
                NavHostKeyCar(navHost)
            }
        }
    }
}
