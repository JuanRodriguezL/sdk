package com.example.modalcard

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.modalcard.ui.theme.ModalCardTheme


class PanFormBottomSheet(
    private val onSubmit: ((String, String) -> Unit)? = null
) : BottomSheetDialogFragment() {
    override fun onCreateView(inflater: android.view.LayoutInflater, container: android.view.ViewGroup?, savedInstanceState: Bundle?): View {
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

        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Solicitud de Tarjeta PAN", style = MaterialTheme.typography.bodyLarge)

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre completo") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = documento,
                onValueChange = { documento = it },
                label = { Text("Documento") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    onSubmit(nombre, documento)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enviar")
            }
        }
    }
}
