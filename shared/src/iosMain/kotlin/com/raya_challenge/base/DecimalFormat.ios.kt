package com.raya_challenge.base
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter

actual class NumberFormat actual constructor() {
    actual fun format(double: Double): String {
        val formatter = NSNumberFormatter()
        formatter.minimumFractionDigits = 0u
        formatter.maximumFractionDigits = 10u
        formatter.numberStyle = 1u //Decimal
        return formatter.stringFromNumber(NSNumber(double))!!
    }
}