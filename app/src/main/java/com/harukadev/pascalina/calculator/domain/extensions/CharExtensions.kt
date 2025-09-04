package com.harukadev.pascalina.calculator.domain.extensions

/**
 * Checks if this character is an ASCII digit ('0' through '9').
 *
 * @return `true` if this character is an ASCII digit, `false` otherwise.
 */
fun Char.isAsciiDigit(): Boolean = this in '0'..'9'