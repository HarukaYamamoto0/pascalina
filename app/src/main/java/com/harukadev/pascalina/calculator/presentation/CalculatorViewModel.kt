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
        _state.update { currentState ->
            currentState.copy(
                calculation = currentState.calculation + input
            )
        }
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