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
