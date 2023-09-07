package com.example.imccompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imccompose.calculo.CalculoIMC
import com.example.imccompose.ui.theme.IMCComposeTheme
import com.example.imccompose.ui.theme.blueDark
import com.example.imccompose.ui.theme.blueLight
import com.example.imccompose.ui.theme.white

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMCComposeTheme {
            LayoutCalculator()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LayoutCalculator(){

    val context = LocalContext.current

    val calcularImc = CalculoIMC()

    var peso by remember {
        mutableStateOf("")
    }

    var altura by remember {
        mutableStateOf("")
    }

    var imcResult by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor =  blueLight,
                title = {
                    Text(text = "Calculadora de IMC", color = white)
                },
                actions = {
                    IconButton(onClick = {
                        peso = ""
                        altura = ""
                        imcResult = ""
                    }
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_refresh_24),
                            contentDescription = "Ícone de refresh"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

            ) {
            Text(
                text = "Calculadora de IMC",
                fontSize = 25.sp,
                color = blueDark,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(50.dp)
                )
            OutlinedTextField(
                value = peso,
                onValueChange = {
                    peso = it
                },
                label = {
                    Text(text = "Peso (kg)")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = blueLight,
                    focusedBorderColor = blueLight,
                    textColor = blueDark,
                    focusedLabelColor = blueDark
                ),
                textStyle = TextStyle(blueDark, 18.sp),
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            OutlinedTextField(
                value = altura,
                onValueChange = {
                    altura = it
                },
                label = {
                    Text(text = "Altura")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = blueLight,
                    focusedBorderColor = blueLight,
                    textColor = blueDark,
                    focusedLabelColor = blueDark
                ),
                textStyle = TextStyle(blueDark, 18.sp),
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp, 20.dp, 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Button(onClick = {
                if (peso.isEmpty() || altura.isEmpty()){
                    Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                }else{
                    calcularImc.calculoIMC(peso,altura)
                    imcResult = calcularImc.resultadoImc()
                }
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = blueLight,
                    contentColor = white
                )
            ) {
                Text(
                    text = "Calcular IMC",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                    )
            }

            Text(
                text = imcResult,
                color = blueDark,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IMCComposeTheme {
    LayoutCalculator()
    }
}