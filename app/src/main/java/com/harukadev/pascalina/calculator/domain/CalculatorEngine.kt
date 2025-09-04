package com.harukadev.pascalina.calculator.domain

import android.util.Log
import com.ezylang.evalex.Expression
import com.ezylang.evalex.config.ExpressionConfiguration
import com.harukadev.pascalina.calculator.domain.extensions.lastOrBlank

class CalculatorEngine(
    private val expressionConfiguration: ExpressionConfiguration
) {
    private val operators = arrayOf('+', '-', '*', '/', '(', ')', '%', '.')
    private val acceptedNumbers = 0..9

    fun calculate(expression: String): Double {
        return evaluateExpression(expression)
    }

    fun openOrCloseParenthesis(expression: String): String {
        if (expression.isEmpty()) return expression

        val openCount = expression.count { it == '(' }
        val closeCount = expression.count { it == ')' }
        val lastChar = expression.lastOrBlank()

        val shouldInsertOpen = when {
            // If there is nothing yet, start with (
            expression.isEmpty() -> true

            // If the last character is an operator or a (
            lastChar in operators -> true

            // If there is more ) than (, add (
            openCount <= closeCount -> true

            // Otherwise, it closes)
            else -> false
        }

        val updated = expression + if (shouldInsertOpen) "(" else ")"
        return updated
    }

    private fun evaluateExpression(expression: String): Double {
        return try {
            val result = Expression(expression, expressionConfiguration).evaluate()
            result.numberValue?.toDouble() ?: 0.0
        } catch (e: Exception) {
            Log.e("com.harukadev.pascalina:CalculatorViewModel:evaluate", e.toString())
            0.0
        }
    }

    fun appendNumber(number: Int, expression: String): String {
        val lastChar = expression.lastOrBlank()

        if (number !in acceptedNumbers) return expression

        return expression + number
    }

    fun appendOperator(operator: Char, expression: String): String {
        val lastChar = expression.lastOrBlank()

        if (operator !in operators) return expression
        if (lastChar in operators) return expression
        if (expression.isEmpty() && operator != '-') return expression

        return expression + operator
    }
}

