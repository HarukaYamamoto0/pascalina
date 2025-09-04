package com.harukadev.pascalina.calculator.domain.extensions

/**
 * Returns the last character of the string, or a blank character ('\u0000') if the string is empty.
 *
 * @receiver The string to get the last character from.
 * @return The last character of the string, or a blank character if the string is empty.
 */
fun String.lastOrBlank() = this.lastOrNull() ?: '\u0000'