package com.example.modalcard

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.modalcard.ui.theme.ModalCardTheme
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HelloSdkBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: android.view.LayoutInflater, container: android.view.ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ModalCardTheme {
                    Surface(modifier = Modifier.fillMaxWidth()) {
                        HelloSdkContent()
                    }
                }
            }
        }
    }

    @Composable
    fun HelloSdkContent() {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // ✅ Imagen desde el SDK
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )

            Text("Hola Mundo", style = MaterialTheme.typography.headlineSmall)

            Button(
                onClick = {
                    PanFormBottomSheet().show(parentFragmentManager, "PanFormSheet")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Abrir formulario Tarjeta")
            }
        }
    }
}
