package com.raya_challenge.base

import java.text.NumberFormat
import java.util.Locale

actual class NumberFormat actual constructor() {
    actual fun formatToString(input: Double): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.US)
        numberFormat.maximumFractionDigits = 10
        return numberFormat.format(input)
    }

    actual fun formatToDouble(input: String): Double {
        val format = NumberFormat.getInstance(Locale.US)
        val number = format.parse(input.ifEmpty { "0.0" })
        return number?.toDouble() ?: 0.0
    }
}