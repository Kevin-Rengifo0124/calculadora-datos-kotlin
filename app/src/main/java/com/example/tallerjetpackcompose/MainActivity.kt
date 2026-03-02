package com.example.tallerjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tallerjetpackcompose.ui.theme.TallerJetpackComposeTheme

/**
 * Clase sellada que representa las pantallas disponibles en la aplicación.
 *
 * Se utiliza para centralizar y organizar las rutas de navegación,
 * evitando el uso de Strings sueltos en el NavController.
 *
 * @property ruta String que identifica la ruta asociada a cada pantalla.
 */
sealed class Pantallas(val ruta: String){
    object INICIO: Pantallas("Inicio")
    object CALCULAR_EDAD: Pantallas("CalcularEdad")
    object CALCULAR_IMC: Pantallas("CalcularIMC")

}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TallerJetpackComposeTheme {

                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Surface(
                        modifier = Modifier.padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavegacionPrincipal(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun NavegacionPrincipal(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Pantallas.INICIO.ruta
    ) {
        composable(Pantallas.INICIO.ruta) {
            Inicio(
                abrirCalcularEdad = {
                    navController.navigate(Pantallas.CALCULAR_EDAD.ruta){
                        popUpTo(Pantallas.INICIO.ruta)
                    }
                },
                abrirCalcularIMC = {
                    navController.navigate(Pantallas.CALCULAR_IMC.ruta){
                        popUpTo(Pantallas.INICIO.ruta)
                    }
                }
            )
        }
        composable(Pantallas.CALCULAR_EDAD.ruta) {
            CalcularEdad()
        }
        composable(Pantallas.CALCULAR_IMC.ruta) {
            CalcularIMC()
        }
    }
}

