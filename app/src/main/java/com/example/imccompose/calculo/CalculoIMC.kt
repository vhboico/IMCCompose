package com.example.imccompose.calculo

import java.text.DecimalFormat

class CalculoIMC {

    private var resultadoIMC = ""

    fun calculoIMC(peso: String, altura: String){

        val pesoConvertido = peso.toDouble()
        val alturaConvertido = altura.toDouble()

        val resultado: String

        val imc = pesoConvertido / (alturaConvertido * alturaConvertido)
        val decimalFormat = DecimalFormat("0.00")

        if(imc <= 18.5){
            resultado = "Peso Abaixo \n IMC: ${decimalFormat.format(imc)}"
        }else if (imc <= 24.9){
            resultado = "Peso Normal \n IMC: ${decimalFormat.format(imc)}"
        }else if (imc <= 29.9){
            resultado = "Sobrepeso \n IMC: ${decimalFormat.format(imc)}"
        }else if (imc <= 34.9){
            resultado = "Obesidade (Grau 1) \n IMC: ${decimalFormat.format(imc)}"
        }else if (imc <= 39.9){
            resultado = "Obesidade Severa (Grau 2) \n IMC: ${decimalFormat.format(imc)}"
        }else {
            resultado = "Obesidade Mórbida (Grau 3) \n IMC: ${decimalFormat.format(imc)}"
        }

        resultadoIMC = resultado
    }

    fun resultadoImc(): String{
        return  resultadoIMC
    }
}