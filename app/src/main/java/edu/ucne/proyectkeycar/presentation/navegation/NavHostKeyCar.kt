package edu.ucne.proyectkeycar.presentation.navegation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.proyectkeycar.presentation.keyCar.AddKeyCarScreen
import edu.ucne.proyectkeycar.presentation.keyCar.ListKeyCarScreen
import edu.ucne.proyectkeycar.presentation.keyType.AddKeyTypeScreen
import edu.ucne.proyectkeycar.presentation.keyType.ListKeyTypeScreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavHostKeyCar(
    navHostController: NavHostController
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(text = "Menu")
                Spacer(modifier = Modifier.padding(10.dp))

                NavigationDrawerItem(
                    label = { Text(text = "Lista tipos de llave") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                        navHostController.navigate(Screen.KeyTypeList)
                    }
                )

                NavigationDrawerItem(
                    label = { Text(text = "Lista LLaves") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                        navHostController.navigate(Screen.KeyCarList)
                    }
                )
            }
        },
    ) {
        TopAppBar(
            title = {"Menu"},
            navigationIcon = {
                IconButton(
                    onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Abrir menú")
                }
            },

            )
        Column(

        ) {
            Box(modifier = Modifier.padding(top = 80.dp)) {





                NavHost(
                    navController = navHostController,
                    startDestination = Screen.KeyTypeList
                ) {
                    composable<Screen.KeyTypeList> {
                       ListKeyTypeScreen(
                           goToAdd = { navHostController.navigate(Screen.KeyType(0)) },
                           onSelect = { navHostController.navigate(Screen.KeyType(it))}
                       )
                    }

                    composable<Screen.KeyType> {
                        AddKeyTypeScreen(
                            goToBack = { navHostController.navigate(Screen.KeyTypeList) }
                        )
                    }

                    composable<Screen.KeyCarList> {
                        ListKeyCarScreen(
                            goToAdd = { navHostController.navigate(Screen.KeyCar(0)) },
                            onSelect = { navHostController.navigate(Screen.KeyCar(it)) }
                        )
                    }

                    composable<Screen.KeyCar> {
                        AddKeyCarScreen(
                            goToBack = { navHostController.navigate(Screen.KeyCarList) }
                        )
                    }
                }
            }
        }
    }
}
