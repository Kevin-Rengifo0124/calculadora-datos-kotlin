import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
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
fun CalcularIMC() {

    var nombre by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var estatura by remember { mutableStateOf("") }
    var resultadoImc by remember { mutableStateOf<Double?>(null) }
    var categoria by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = Color(0xFF0D1B2A),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Calculadora IMC",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2596BE),
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            BotonCalcularImc(
                nombre = nombre,
                peso = peso,
                estatura = estatura,
                onResultado = { imc, cat ->
                    resultadoImc = imc
                    categoria = cat
                },
                snackbarHostState = snackbarHostState,
                scope = scope
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            FormularioImc(
                nombre = nombre,
                peso = peso,
                estatura = estatura,
                onNombreChange = { nombre = it },
                onPesoChange = { peso = it },
                onEstaturaChange = { estatura = it }
            )

            resultadoImc?.let {
                ResultadoImc(
                    nombre = nombre,
                    imc = it,
                    categoria = categoria
                )
            }
        }
    }
}
@Composable
fun FormularioImc(
    nombre: String,
    peso: String,
    estatura: String,
    onNombreChange: (String) -> Unit,
    onPesoChange: (String) -> Unit,
    onEstaturaChange: (String) -> Unit
) {

    val coloresCampos = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color(0xFF2596BE),
        unfocusedBorderColor = Color(0xFF2596BE),
        focusedLabelColor = Color(0xFF2596BE),
        unfocusedLabelColor = Color(0xFF2596BE),
        cursorColor = Color(0xFF2596BE),
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White
    )

    OutlinedTextField(
        value = nombre,
        onValueChange = onNombreChange,
        label = { Text("Nombre") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        colors = coloresCampos
    )

    OutlinedTextField(
        value = peso,
        onValueChange = onPesoChange,
        label = { Text("Peso (kg)") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        colors = coloresCampos
    )

    OutlinedTextField(
        value = estatura,
        onValueChange = onEstaturaChange,
        label = { Text("Estatura (m)") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        colors = coloresCampos
    )
}

@Composable
fun ResultadoImc(
    nombre: String,
    imc: Double,
    categoria: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2596BE)
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "Resultado",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text("Nombre: $nombre", color = Color.White)
            Text("IMC: %.2f".format(imc), color = Color.White)
            Text("Categoría: $categoria", color = Color.White)
        }
    }
}

@Composable
fun BotonCalcularImc(
    nombre: String,
    peso: String,
    estatura: String,
    onResultado: (Double, String) -> Unit,
    snackbarHostState: SnackbarHostState,
    scope: kotlinx.coroutines.CoroutineScope
) {

    FloatingActionButton(
        onClick = {

            val pesoNum = peso.toDoubleOrNull()
            val estaturaNum = estatura.toDoubleOrNull()

            if (pesoNum != null && estaturaNum != null && estaturaNum > 0) {

                val imc = pesoNum / (estaturaNum * estaturaNum)

                val categoria = when {
                    imc < 18.5 -> "Bajo peso"
                    imc < 25 -> "Normal"
                    imc < 30 -> "Sobrepeso"
                    else -> "Obesidad"
                }

                onResultado(imc, categoria)

                scope.launch {
                    snackbarHostState.showSnackbar("Cálculo realizado correctamente")
                }

            } else {
                scope.launch {
                    snackbarHostState.showSnackbar("Ingrese datos válidos")
                }
            }
        },
        containerColor = Color(0xFF2596BE)
    ) {
        Icon(
            imageVector = Icons.Default.Calculate,
            contentDescription = "Calcular IMC",
            tint = Color.White
        )
    }
}
