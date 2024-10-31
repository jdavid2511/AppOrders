package com.ordersapp.components


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ordersapp.R
import com.ordersapp.app.PostOfficeAppRouter
import com.ordersapp.app.Screen
import com.ordersapp.ui.theme.GrayColor
import com.ordersapp.ui.theme.Primary
import com.ordersapp.ui.theme.Secundary
import com.ordersapp.ui.theme.TextColor
import com.ordersapp.ui.theme.WhiteColor
import com.ordersapp.ui.theme.bgPrimary
import com.ordersapp.ui.theme.btnColor
import com.ordersapp.ui.theme.textbtn


val rubik = FontFamily(
    Font(R.font.rubik_bold, FontWeight.Bold),
    Font(R.font.rubik_regular, FontWeight.Normal),
    Font(R.font.rubik_black, FontWeight.Black),
    Font(R.font.rubik_medium, FontWeight.Normal),
    Font(R.font.rubik_light, FontWeight.Light),
)
@Composable
fun NormalTextComponents(value: String, heightInt: Int) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = heightInt.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = Color.Black,
       textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponents(value: String) {
    Text(
        text = value,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
        ),
        color = Color.Black,
        textAlign = TextAlign.Center,
        fontFamily = rubik
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextComponents(labelValue: String, painterResource: Painter) {

    val roundedShape: Shape = RoundedCornerShape(50.dp)

    val textValue = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .heightIn(min = 40.dp),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Gray,
            focusedLabelColor = Color.Gray,
            cursorColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.height(20.dp),
                painter = painterResource,
                contentDescription = ""
            )
        },
        shape = roundedShape
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextComponents(labelValue: String, painterResource: Painter) {

    val roundedShape: Shape = RoundedCornerShape(50.dp)
    val localFocusManager = LocalFocusManager.current
    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }
    OutlinedTextField (
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .heightIn(min = 40.dp),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Gray,
            focusedLabelColor = Color.Gray,
            cursorColor = Color.Black
        ),
        shape = roundedShape,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        singleLine = true,
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
        },
        leadingIcon = {
            Icon (
                modifier = Modifier.height(20.dp),
                painter = painterResource,
                contentDescription = ""
            )
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Outlined.VisibilityOff
            }

            var description = if (passwordVisible.value){
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }
            
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription =description)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun ClickableTextComponent(value: String) {
    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy "
    val andText = "and "
    val termAndConditionsText = "Terms of Use"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = termAndConditionsText, annotation = termAndConditionsText)
            append(termAndConditionsText)
        }
    }

    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.also { span ->
            Log.d("ClickableTextComponent", "{$span}")
        }
    })
}

@Composable
fun CheckComponent(value: String) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .padding(9.dp),
        verticalAlignment = Alignment.CenterVertically,
        ) {
        val checkedState = remember {
            mutableStateOf(false)
        }
        Checkbox(checked = checkedState.value, onCheckedChange = {checkedState.value = !checkedState.value} )
        ClickableTextComponent(value = value)
    }
}

@Composable
fun buttonComponent(value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(48.dp)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Secundary, Primary)),
                        shape = RoundedCornerShape(50.dp)
                    ),

                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = value,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun dividerTextComponent() {
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = GrayColor,
            thickness = 1.dp
        )

        Text(modifier = Modifier.padding(all = 5.dp), text = "or", fontSize = 14.sp, color = TextColor)

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = GrayColor,
            thickness = 1.dp
        )
    }
}

@Composable
fun ClickableLoginTextComponent(isLogin: Boolean, onTextSeleccted: (String) -> Unit) {
    val initialText = if (!isLogin) "Already have an account? " else "Don't have an account yet? "
    val loginText = if (!isLogin) "Login" else "Register"


    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 19.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.also { span ->
            Log.d("ClickableTextComponent", "{$span}")
            if (span.item == loginText) {
                onTextSeleccted(span.item)
            }
        }
        })
}

@Composable
fun ClickableForgetPassTextComponent(onTextSeleccted: (String) -> Unit) {
    val initialText = "Forget your password?"


    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = GrayColor)) {
            pushStringAnnotation(tag = initialText, annotation = initialText)
            append(initialText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 19.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        ),
        text = annotatedString, onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{$span}")
                if (span.item == initialText) {
                    onTextSeleccted(span.item)
                }
            }
        }
    )
}

@Composable
fun buttonaddComponent(withInt: Int, padding: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .padding(padding.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { },
            modifier = Modifier
                .widthIn(withInt.dp)
                .heightIn(70.dp),
            shape = RoundedCornerShape(25.dp),
            contentPadding = PaddingValues(),
        ) {
            Box(
                modifier = Modifier
                    .widthIn(withInt.dp)
                    .heightIn(70.dp)
                    .background(
                        color = btnColor,
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun DoubleTextComponents(value1: String, value2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), // Add horizontal padding
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = value1,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Black,
            fontFamily = rubik
        )

        Text(
            text = value2,
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            color = btnColor,
            fontFamily = rubik
        )
    }
}

@Composable
fun boxChairComponent(chair: String, check: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(46.dp)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                PostOfficeAppRouter.navigateTo(Screen.TableOrderScreen)
            },
            modifier = Modifier
                .widthIn(178.dp)
                .heightIn(45.dp)
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(50.dp),
                    ambientColor = btnColor,
                    spotColor = btnColor,
                ),
            contentPadding = PaddingValues(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(50.dp)
                    .background(
                        color = WhiteColor,
                        shape = RoundedCornerShape(50.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp), // Add horizontal padding
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = chair,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Black
                    )

                    Text(
                        text = check,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Black
                    )
                }
            }
        }
    }
}

@Composable
fun TopAccessComponent(table: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), // Add horizontal padding
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = table,
            fontSize = 35.sp,
            fontWeight = FontWeight.Normal,
            color = Black,
            fontFamily = rubik
        )
        Button(
            onClick = {
                PostOfficeAppRouter.navigateTo(Screen.RegisterFoodScreen)
            },
            modifier = Modifier
                .widthIn(44.dp)
                .heightIn(44.dp),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(),
        ) {
            Box(
                modifier = Modifier
                    .widthIn(44.dp)
                    .heightIn(44.dp)
                    .background(
                        color = bgPrimary,
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "X",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Thin,
                    color = textbtn,
                )
            }
        }
    }
}

@Composable
fun productTableComponent(product: String, price: String, quantity: String) {
    var contQuatity : Int = quantity.toInt()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(40.dp)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(50.dp)
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(50.dp),
                    ambientColor = btnColor,
                    spotColor = btnColor,
                )
                .background(
                    color = WhiteColor,
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), // Add horizontal padding
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Text(
                        text = product,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Black,
                        fontFamily = rubik
                    )

                    Text(
                        text = price,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = btnColor,
                        fontFamily = rubik
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp), // Add horizontal padding
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Button(
                        onClick = {contQuatity--},
                        modifier = Modifier
                            .widthIn(24.dp)
                            .heightIn(24.dp),
                        shape = RoundedCornerShape(20.dp),
                        contentPadding = PaddingValues(),
                    ) {
                        Box(
                            modifier = Modifier
                                .widthIn(24.dp)
                                .heightIn(24.dp)
                                .background(
                                    color = bgPrimary,
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.width(10.dp),
                                painter = painterResource(id = R.drawable.minus),
                                contentDescription = "minus",
                                tint = btnColor
                            )
                        }
                    }

                    Text(
                        text = contQuatity.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Black,
                        fontFamily = rubik
                    )

                    Button(
                        onClick = {contQuatity++},
                        modifier = Modifier
                            .widthIn(24.dp)
                            .heightIn(24.dp),
                        shape = RoundedCornerShape(20.dp),
                        contentPadding = PaddingValues(),
                    ) {
                        Box(
                            modifier = Modifier
                                .widthIn(24.dp)
                                .heightIn(24.dp)
                                .background(
                                    color = btnColor,
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.width(10.dp),
                                painter = painterResource(id = R.drawable.plus),
                                contentDescription = "Plus"
                            )
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun TotalAccountComponent(value: String, total: String) {
    Surface (
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = WhiteColor,
        shadowElevation = 16.dp
    ) {
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(10.dp),
            shape = RoundedCornerShape(20.dp),
            contentPadding = PaddingValues(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = btnColor,
                        shape = RoundedCornerShape(20.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(
                            horizontal = 60.dp,
                            vertical = 16.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = value,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = WhiteColor
                    )

                    Text(
                        text = total,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = WhiteColor
                    )
                }
            }
        }
    }
}
