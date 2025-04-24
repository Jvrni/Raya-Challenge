package com.raya_challenge.base

import java.text.NumberFormat
import java.util.Locale

actual class NumberFormat actual constructor() {
    actual fun formatToString(double: Double): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.US)
        numberFormat.maximumFractionDigits = 10
        return numberFormat.format(double)
    }

    actual fun formatToDouble(string: String): Double {
        val format = NumberFormat.getInstance(Locale.US)
        val number = format.parse(string.ifEmpty { "0.0" })
        return number?.toDouble() ?: 0.0
    }
}