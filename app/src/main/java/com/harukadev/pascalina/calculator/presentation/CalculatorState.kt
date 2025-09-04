package com.harukadev.pascalina.calculator.presentation

data class CalculatorState(
    var expression: String = "10+10",
    var calculationResult: String = "20",
)