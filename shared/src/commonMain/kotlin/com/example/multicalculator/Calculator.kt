package com.example.multicalculator

class Calculator {

    fun Add(left:Number, right: Number):Number
    {
        return left.toDouble() + right.toDouble()
    }
    fun subtract(left: Number, right: Number): Number {
        return left.toDouble() - right.toDouble()
    }

    fun divide(left: Number, right: Number): Number {
        require(right.toDouble() != 0.0) {
            "Divide by zero exception"
        }
        return left.toDouble() / right.toDouble()
    }

    fun multiply(left: Number, right: Number): Number {
        return left.toDouble() * right.toDouble()
    }

}