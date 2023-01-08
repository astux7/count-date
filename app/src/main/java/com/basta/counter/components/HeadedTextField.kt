package com.basta.counter.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun HeadedTextField(
    modifier: Modifier = Modifier,
    header: String? = "",
    value: String = "",
    onValueChange: (String) -> Unit = {},
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions : KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Column(modifier) {
        header?.let {
            Text(
                text = header,
                style = textStyle,
                color = MaterialTheme.colors.secondary
            )
        }

        OutlinedTextField(
            value = value,
            modifier = Modifier.fillMaxWidth()
                .defaultMinSize(minHeight = 40.dp),
            onValueChange = onValueChange,
            label = label,
            visualTransformation = visualTransformation,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onSecondary,
                backgroundColor = Color.Transparent,
                unfocusedBorderColor = MaterialTheme.colors.onSecondary,
                focusedBorderColor = MaterialTheme.colors.primary,
                cursorColor = MaterialTheme.colors.primary,
            ),
            readOnly = readOnly
        )
    }
}
