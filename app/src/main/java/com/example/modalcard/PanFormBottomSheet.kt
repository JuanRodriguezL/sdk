package com.example.modalcard

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.example.modalcard.ui.theme.ModalCardTheme
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class PanFormBottomSheet(
    private val onSubmit: ((String, String) -> Unit)? = null
) : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ModalCardTheme {
                    Surface {
                        FormularioPan { nombre, documento ->
                            onSubmit?.invoke(nombre, documento)
                            dismiss()
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun FormularioPan(onSubmit: (String, String) -> Unit) {
        var nombre by remember { mutableStateOf("") }
        var documento by remember { mutableStateOf("") }
        var numeroTarjeta by remember { mutableStateOf("") }
        var fecha by remember { mutableStateOf("") }
        var cvv by remember { mutableStateOf("") }

        val numeroVisible = numeroTarjeta.padEnd(16, '*').chunked(4).joinToString(" ")

        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                IconButton(onClick = { dismiss() }) {
                    Icon(Icons.Default.Close, contentDescription = "Cerrar")
                }
            }

            Text("Hola Mundo", style = MaterialTheme.typography.headlineSmall)

            // 💳 Vista tipo tarjeta
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(numeroVisible, style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(nombre.uppercase(), style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.height(4.dp))
                    Row {
                        Text(fecha, modifier = Modifier.weight(1f))
                        Text(cvv.replace(Regex("."), "*"), modifier = Modifier.weight(1f))
                    }
                }
            }

            // 🧾 Inputs
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre completo") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = documento,
                onValueChange = { documento = it },
                label = { Text("Documento") },
                leadingIcon = { Icon(Icons.Default.Info, contentDescription = "Documento") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = numeroTarjeta,
                onValueChange = { if (it.length <= 16 && it.all { c -> c.isDigit() }) numeroTarjeta = it },
                label = { Text("Número de tarjeta") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha de vencimiento (MM/YY)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = cvv,
                onValueChange = { if (it.length <= 4 && it.all { c -> c.isDigit() }) cvv = it },
                label = { Text("CVV") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    onSubmit(nombre, documento) // puedes expandir esto si quieres mandar más info
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enviar")
            }
        }
    }

}