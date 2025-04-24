package com.raya_challenge.base

expect class NumberFormat() {
    fun formatToString(double: Double) : String
    fun formatToDouble(string: String) : Double
}