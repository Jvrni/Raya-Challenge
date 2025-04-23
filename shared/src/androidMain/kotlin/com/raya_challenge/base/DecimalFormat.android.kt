package com.raya_challenge.base

import java.text.NumberFormat
import java.util.Locale

actual class NumberFormat actual constructor() {
    actual fun format(double: Double): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.US)
        numberFormat.maximumFractionDigits = 10
        return numberFormat.format(double)
    }
}