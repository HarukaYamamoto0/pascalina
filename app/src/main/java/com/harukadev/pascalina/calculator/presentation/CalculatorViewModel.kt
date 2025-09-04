package com.harukadev.pascalina.calculator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ezylang.evalex.config.ExpressionConfiguration
import com.harukadev.pascalina.calculator.domain.CalculatorEngine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalculatorViewModel(
    private val calculatorEngine: CalculatorEngine
) : ViewModel() {
    private val _state = MutableStateFlow(CalculatorState())
    val state: StateFlow<CalculatorState> = _state.asStateFlow()

    fun calculate() {
        val result = calculatorEngine.calculate(_state.value.expression)
        val resultAsString = if (result == result.toInt().toDouble()) {
            result.toInt().toString()
        } else {
            result.toString()
        }
        _state.update { it -> it.copy(calculationResult = resultAsString) }
    }

    fun onClearKey() {
        _state.update { it -> it.copy(expression = "", calculationResult = "0") }
    }

    fun onBackspaceKey() {
        _state.update { it -> it.copy(expression = _state.value.expression.dropLast(1)) }
    }

    fun onParenthesisKey() {
        val updatedExpression =
            calculatorEngine.openOrCloseParenthesis(expression = _state.value.expression)
        _state.update { it -> it.copy(expression = updatedExpression) }
        calculate()
    }

    fun appendNumber(number: Int) {
        val updatedExpression = calculatorEngine.appendNumber(number, _state.value.expression)
        _state.update { it -> it.copy(expression = updatedExpression) }
        calculate()
    }

    fun appendOperator(operator: Char) {
        val updatedExpression = calculatorEngine.appendOperator(operator, _state.value.expression)
        _state.update { it -> it.copy(expression = updatedExpression) }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CalculatorViewModel(
                    calculatorEngine = CalculatorEngine(
                        expressionConfiguration = ExpressionConfiguration.builder()
                            .decimalPlacesRounding(2)
                            .build()
                    )
                )
            }
        }
    }
}