package com.example.tallerjetpackcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp

@Composable
fun Inicio(abrirCalcularEdad:() -> Unit, abrirCalcularIMC: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF0D1B2A)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Logotipo
            Image(
                painter = painterResource(id = R.drawable.icono),
                contentDescription = "Icono de la aplicación",
                modifier = Modifier
                    .height(160.dp)
                    .padding(bottom = 40.dp)
            )

            BotonMenu("Calcular Edad"){
                abrirCalcularEdad()
            }

            Spacer(modifier = Modifier.height(20.dp))

            BotonMenu("Calcular IMC"){
                abrirCalcularIMC()
            }
        }
    }
}

@Composable
fun BotonMenu(titulo: String, action: ()-> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2596BE)
        ),
        onClick = {action()}
    ) {
        Text(
            text = titulo,
            color = Color.White,
            fontSize = 18.sp
        )
    }
}