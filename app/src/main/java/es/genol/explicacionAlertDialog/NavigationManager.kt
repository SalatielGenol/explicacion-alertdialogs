package es.genol.explicacionAlertDialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/* ###############  Elementos necesarios para el funcionamiento de la App ############### */

/* Screen inicial mediante la que se accede a los diferentes ejemplos */

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate(route = Screens.SimpleAlertDialog.path) }) {
            Text(text = "Tipos de Alert Dialog")
        }
        Button(onClick = { navController.navigate(route = Screens.AlertDialogReturnScreen.path) }) {
            Text(text = "Alert Dialog con retorno")
        }
        Button(onClick = { navController.navigate(route = Screens.TapGesturesScreen.path) }) {
            Text(text = "Tipos de gestos sobre un boton")
        }
    }
}

/* Clase que contiene los objetos Screen para el navcontroller */

sealed class Screens(val path: String) {
    object MainScreen : Screens(path = "MainScreen")
    object SimpleAlertDialog : Screens(path = "SimpleAlertDialog")
    object AlertDialogReturnScreen : Screens(path = "AlertDialogReturnScreen")
    object TapGesturesScreen : Screens(path = "TapGesturesScreen")
}

/* Navigation */

@Composable
fun NavigationManager() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screens.MainScreen.path) {
        composable(route = Screens.MainScreen.path) { MainScreen(navController) }
        composable(route = Screens.SimpleAlertDialog.path) { SimpleAlertDialogScreen() }
        composable(route = Screens.AlertDialogReturnScreen.path) { AlertDialogReturnScreen() }
        composable(route = Screens.TapGesturesScreen.path) { TapGesturesScreen() }
    }
}