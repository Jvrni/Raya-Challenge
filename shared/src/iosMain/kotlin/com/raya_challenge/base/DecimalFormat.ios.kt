package com.raya_challenge.base
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle
import platform.darwin.NSUInteger

actual class NumberFormat actual constructor() {
    private val minFractionDigits: NSUInteger = 0u
    private val maxFractionDigits: NSUInteger = 10u
    private val style: NSUInteger = 1u

    actual fun formatToString(input: Double): String {
        val formatter = NSNumberFormatter().apply {
            minimumFractionDigits = minFractionDigits
            maximumFractionDigits = maxFractionDigits
            numberStyle = style
        }

        return formatter.stringFromNumber(NSNumber(input)) ?: "0.0"
    }

    actual fun formatToDouble(input: String): Double {
        if (input.isEmpty()) return 0.0

        val formatter = NSNumberFormatter().apply {
            minimumFractionDigits = minFractionDigits
            maximumFractionDigits = maxFractionDigits
            numberStyle = style
        }

        return formatter.numberFromString(input)?.doubleValue ?: 0.0
    }
}