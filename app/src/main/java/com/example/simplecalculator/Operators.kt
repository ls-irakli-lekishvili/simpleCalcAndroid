package com.example.simplecalculator

/**
 * Enum for operators that are supported by simple calculator
 */

enum class Operators(val char: Char) {
    ADD('+'), SUBTRACT('-'), MULTIPLY('*'), DIVIDE('/')
}