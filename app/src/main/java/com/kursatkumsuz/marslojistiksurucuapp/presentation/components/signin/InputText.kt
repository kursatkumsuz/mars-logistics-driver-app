package com.kursatkumsuz.marslojistiksurucuapp.presentation.components.signin

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun InputText(
    labelText: String,
    onInput: (String) -> Unit
) {
    var textState by remember { mutableStateOf("") }

    OutlinedTextField(
        value = textState.trim(),
        onValueChange = {
            textState = it
            onInput(it)
        },
        label = { Text(text = labelText) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 30.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Gray,
            unfocusedTextColor = Color.LightGray,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Gray,
            unfocusedIndicatorColor = Color.LightGray,
            disabledIndicatorColor = Color.Transparent,
            focusedLeadingIconColor = Color.Transparent,
            unfocusedLeadingIconColor = Color.Transparent,
            focusedTrailingIconColor = Color.Transparent,
            unfocusedTrailingIconColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(15.dp)
    )
}