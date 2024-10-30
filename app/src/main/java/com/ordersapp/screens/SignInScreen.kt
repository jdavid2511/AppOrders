package com.ordersapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ordersapp.R
import com.ordersapp.app.PostOfficeApp
import com.ordersapp.app.PostOfficeAppRouter
import com.ordersapp.app.Screen
import com.ordersapp.components.CheckComponent
import com.ordersapp.components.ClickableForgetPassTextComponent
import com.ordersapp.components.ClickableLoginTextComponent
import com.ordersapp.components.EditTextComponents
import com.ordersapp.components.HeadingTextComponents
import com.ordersapp.components.NormalTextComponents
import com.ordersapp.components.PasswordTextComponents
import com.ordersapp.components.buttonComponent
import com.ordersapp.components.dividerTextComponent

@Composable
fun SignInScreen (){
    Surface (
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 50.dp, start = 28.dp, end = 28.dp)
    ) {
        Column (modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {
            NormalTextComponents(value = stringResource(id = R.string.Hey_there))
            HeadingTextComponents(value = stringResource(id = R.string.welcome))
            EditTextComponents(labelValue = stringResource(id = R.string.email), painterResource = painterResource(
                id = R.drawable.mail
            )
            )
            PasswordTextComponents(labelValue = stringResource(id = R.string.password), painterResource = painterResource(
                id = R.drawable.lock
            )
            )
            Spacer(modifier = Modifier.height(30.dp))

            ClickableForgetPassTextComponent(onTextSeleccted = {})

            Spacer(modifier = Modifier.height(80.dp))

            buttonComponent(value = "Login")

            dividerTextComponent()

            ClickableLoginTextComponent(true, onTextSeleccted = {
                PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
            })
        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfSingInScreen(){
    SignInScreen()
}