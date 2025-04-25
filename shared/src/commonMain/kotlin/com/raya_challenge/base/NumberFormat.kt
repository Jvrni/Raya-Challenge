package com.raya_challenge.base

expect class NumberFormat() {
    fun formatToString(input: Double) : String
    fun formatToDouble(input: String) : Double
}