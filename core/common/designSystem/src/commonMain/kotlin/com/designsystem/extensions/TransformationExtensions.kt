package com.designsystem.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun String.passwordTransformation(condition: Boolean) =
    if (condition) PasswordVisualTransformation().filter(AnnotatedString(this)).text
    else this
