package com.ordersapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ordersapp.R
import com.ordersapp.components.EditTextComponents
import com.ordersapp.components.HeadingTextComponents
import com.ordersapp.components.NormalTextComponents

@Composable
fun SignUpScreen () {
    Surface (
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column (modifier = Modifier.fillMaxSize()) {
            NormalTextComponents(value = stringResource(id = R.string.Hey_there))
            HeadingTextComponents(value = stringResource(id = R.string.welcome))
            EditTextComponents(labelValue = stringResource(id = R.string.first_name))
        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfSingUpScreen(){
    SignUpScreen()
}