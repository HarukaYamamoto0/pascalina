package com.harukadev.pascalina.calculator.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ezylang.evalex.Expression
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalculatorViewModel() : ViewModel() {
    private val _state = MutableStateFlow(CalculatorState())
    val state: StateFlow<CalculatorState> = _state.asStateFlow()

    fun validateInput(input: String) {
        val current = state.value.calculation

        val lastChar = current.lastOrNull()
        val isOperator = { c: Char -> c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == ',' }

        // Avoid starting with an operator (except "-")
        if (current.isEmpty() && isOperator(input.first()) && input != "-") return

        // Avoid two operators in a row
        if (lastChar != null && isOperator(lastChar) && isOperator(input.first())) return

        // Avoid multiple stitches in a row or multiple stitches in the same number
        if (input == ",") {
            val lastNumber = current.takeLastWhile { it.isDigit() || it == ',' }
            if (lastNumber.contains(',')) return
            if (lastChar == null || !lastChar.isDigit()) return
        }

        // Avoid starting with multiple zeros without a period
        if (input == "0") {
            val lastNumber = current.takeLastWhile { it.isDigit() }
            if (lastNumber == "0") return
        }

        // If it passed the validations, update the calculation
        _state.update { currentState ->
            currentState.copy(
                calculation = currentState.calculation + input
            )
        }

        calculate()
    }

    fun onParenthesisClick() {
        val current = state.value.calculation
        val openCount = current.count { it == '(' }
        val closeCount = current.count { it == ')' }
        val lastChar = current.lastOrNull()

        val shouldInsertOpen = when {
            // If there is nothing yet, start with (
            current.isEmpty() -> true

            // If the last character is an operator or a (
            lastChar != null && (lastChar in "+-*/(") -> true

            // If there is more ) than (, add (
            openCount <= closeCount -> true

            // Otherwise, it closes)
            else -> false
        }

        val updated = current + if (shouldInsertOpen) "(" else ")"

        _state.update { it.copy(calculation = updated) }
        calculate()
    }


    fun clear() {
        _state.update { it -> it.copy(calculation = "", calculationResult = "0") }
    }

    fun backspace() {
        _state.update { it -> it.copy(calculation = it.calculation.dropLast(1)) }
        calculate()
    }

    fun calculate() {
        val result = evaluate(_state.value.calculation)
        val resultAsString = if (result == result.toInt().toDouble()) {
            result.toInt().toString()
        } else {
            result.toString()
        }
        _state.update { it -> it.copy(calculationResult = resultAsString) }
    }

    fun evaluate(expression: String): Double {
        return try {
            val result = Expression(expression).evaluate()
            result.numberValue?.toDouble() ?: 0.0
        } catch (e: Exception) {
            Log.e("com.harukadev.pascalina:CalculatorViewModel:evaluate", e.toString())
            0.0
        }
    }
}