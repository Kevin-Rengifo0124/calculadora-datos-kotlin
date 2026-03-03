package com.example.tallerjetpackcompose

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalcularEdad() {

    var nombre by remember { mutableStateOf("") }
    var anioNacimiento by remember { mutableStateOf("") }
    var anioActual by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = Color(0xFF0D1B2A),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Datos Básicos", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2596BE),
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            CalcularEdadFAB(
                nombre = nombre,
                anioNacimiento = anioNacimiento,
                anioActual = anioActual,
                snackbarHostState = snackbarHostState,
                onResultado = { resultado = it },
                scope = scope
            )
        }
    ) { innerPadding ->
        CalcularEdadContent(
            innerPadding = innerPadding,
            nombre = nombre,
            anioNacimiento = anioNacimiento,
            anioActual = anioActual,
            resultado = resultado,
            onNombreChange = { nombre = it },
            onAnioNacimientoChange = { anioNacimiento = it },
            onAnioActualChange = { anioActual = it }
        )
    }
}

@Composable
fun CalcularEdadFAB(
    nombre: String,
    anioNacimiento: String,
    anioActual: String,
    snackbarHostState: SnackbarHostState,
    onResultado: (String) -> Unit,
    scope: kotlinx.coroutines.CoroutineScope
) {
    FloatingActionButton(
        onClick = {
            val nacimiento = anioNacimiento.toIntOrNull()
            val actual = anioActual.toIntOrNull()

            if (nacimiento != null && actual != null) {
                val edad = actual - nacimiento
                onResultado(
                    "Nombre: $nombre\n" +
                            "Año de nacimiento: $anioNacimiento\n" +
                            "Año actual: $anioActual\n" +
                            "Edad: $edad"
                )
                scope.launch {
                    snackbarHostState.showSnackbar("Datos calculados correctamente")
                }
            } else {
                scope.launch {
                    snackbarHostState.showSnackbar("Ingrese años válidos")
                }
            }
        },
        containerColor = Color(0xFF2596BE)
    ) {
        Icon(
            imageVector = Icons.Default.Calculate,
            contentDescription = "Calcular edad",
            tint = Color.White
        )
    }
}

@Composable
fun CalcularEdadContent(
    innerPadding: PaddingValues,
    nombre: String,
    anioNacimiento: String,
    anioActual: String,
    resultado: String,
    onNombreChange: (String) -> Unit,
    onAnioNacimientoChange: (String) -> Unit,
    onAnioActualChange: (String) -> Unit
) {

    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color(0xFF2596BE),
        unfocusedBorderColor = Color(0xFF2596BE),
        focusedLabelColor = Color(0xFF2596BE),
        unfocusedLabelColor = Color(0xFF2596BE),
        cursorColor = Color(0xFF2596BE),
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White
    )

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        OutlinedTextField(
            value = nombre,
            onValueChange = onNombreChange,
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = fieldColors
        )

        OutlinedTextField(
            value = anioNacimiento,
            onValueChange = onAnioNacimientoChange,
            label = { Text("Año de nacimiento") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = fieldColors
        )

        OutlinedTextField(
            value = anioActual,
            onValueChange = onAnioActualChange,
            label = { Text("Año actual") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = fieldColors
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (resultado.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF2596BE)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Resultado",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = resultado,
                        fontSize = 15.sp,
                        lineHeight = 24.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}