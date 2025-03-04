package com.ordersapp.components


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBusiness
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
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
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.ordersapp.R
import com.ordersapp.data.category.Category
import com.ordersapp.data.table.Table
import com.ordersapp.data.tableproductscrossref.TableProductsCrossRef
import com.ordersapp.navigation.Routes
import com.ordersapp.presentation.ProductState
import com.ordersapp.ui.theme.GrayColor
import com.ordersapp.ui.theme.Primary
import com.ordersapp.ui.theme.Secundary
import com.ordersapp.ui.theme.TextColor
import com.ordersapp.ui.theme.WhiteColor
import com.ordersapp.ui.theme.bgPrimary
import com.ordersapp.ui.theme.textbtn
import com.ordersapp.viewModel.ProductViewModel
import com.ordersapp.viewModel.TableViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


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
        color = TextColor,
       textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponents(value: String, fontWeigth: FontWeight) {
    Text(
        text = value,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = fontWeigth,
            fontStyle = FontStyle.Normal,
        ),
        color = TextColor,
        textAlign = TextAlign.Center,
        fontFamily = rubik
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextComponents(labelValue: String, imageVector: ImageVector, keyboardType: KeyboardType, value: String, onValueChange: (String) -> Unit) {

    val roundedShape: Shape = RoundedCornerShape(50.dp)

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .clip(RoundedCornerShape(4.dp))
            .heightIn(min = 40.dp),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Gray,
            focusedLabelColor = Color.Gray,
            cursorColor = TextColor
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                modifier = Modifier.height(20.dp),
                imageVector = imageVector,
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
            cursorColor = TextColor
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
fun buttonaddComponent(withInt: Int, padding: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .padding(padding.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onClick,
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
                        color = Primary,
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
fun DoubleTextComponents(value1: String, value2: String, tableId: Int, tableViewModel: TableViewModel, onClick: (Int) -> Unit) {
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
            color = TextColor,
            fontFamily = rubik
        )

        ClickableText(
            text = AnnotatedString(value2),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                color = Primary,
                fontFamily = rubik
            ),
            onClick = onClick

        )
    }
}

@Composable
fun boxChairComponent(chair: String, tableId: Int, check: String, navHostController: NavHostController) {
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
                navHostController.navigate(Routes.TableOrderScreen.createRoute(tableId))
            },
            modifier = Modifier
                .widthIn(178.dp)
                .heightIn(45.dp)
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(50.dp),
                    ambientColor = Primary,
                    spotColor = Primary,
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
                        text = chair+tableId,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = TextColor
                    )

                    Text(
                        text = check,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextColor
                    )
                }
            }
        }
    }
}

@Composable
fun TopAccessComponent(table: String, navHostController: NavHostController) {
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
            color = TextColor,
            fontFamily = rubik
        )
        Button(
            onClick = {
                navHostController.navigate(Routes.RegisterFoodScreen.route)
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
fun productTableComponent(
    tableViewModel: TableViewModel,
    tableId: Int,
    productId: Int,
    product: String,
    price: Long,
    quantity: Int,
    totalTable: Long
) {
    var contQuantity by remember { mutableStateOf(quantity) }
    var showDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(40.dp)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showDialog) {
            AlertDialogRequest(
                onDismiss = { showDialog = false },
                title = "¿Desea eliminar el producto?",
                onClick = {
                    tableViewModel.deleteProduct(tableId = tableId, productId = productId, productPrice = price)
                    showDialog = false
                }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(50.dp)
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(50.dp),
                    ambientColor = Primary,
                    spotColor = Primary,
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
                        fontWeight = FontWeight.Normal,
                        color = TextColor,
                        fontFamily = rubik
                    )

                    Text(
                        text = price.toString(),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        color = Primary,
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
                        onClick = {
                            if (contQuantity > 1) {
                                tableViewModel.updateTable(
                                    Table(
                                        tableId,
                                        totalTable - price.toLong()
                                    )
                                )
                                contQuantity -= 1
                                tableViewModel.updateTableCrossRef(
                                    TableProductsCrossRef(
                                        tableId,
                                        productId,
                                        contQuantity
                                    )
                                )
                            } else {
                                showDialog = true
                            }
                        },
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
                                tint = Primary
                            )
                        }
                    }

                    Text(
                        text = contQuantity.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = TextColor,
                        fontFamily = rubik
                    )

                    Button(
                        onClick = {
                            tableViewModel.updateTable(Table(tableId, totalTable + price.toLong()))
                            contQuantity += 1
                            tableViewModel.updateTableCrossRef(
                                TableProductsCrossRef(
                                    tableId,
                                    productId,
                                    contQuantity
                                )
                            )
                        },
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
                                    color = Primary,
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
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(10.dp),
            shape = RoundedCornerShape(20.dp),
            contentPadding = PaddingValues(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Primary,
                        shape = RoundedCornerShape(20.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .padding(
                            horizontal = 60.dp,
                            vertical = 10.dp
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

@Composable
fun buttonSaveComponent(onClick: () -> Unit, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .padding(start = 22.dp, end = 22.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(
            onClick = onClick,
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
                        brush = Brush.horizontalGradient(listOf(GrayColor, Primary)),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownWithIdAndName(items: List<Category>, selectedCategoryId: Int, onItemSelected: (Int) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val selectedCategory = items.find { it.id == selectedCategoryId }
    Box(modifier = Modifier
            .fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .heightIn(min = 40.dp),
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedCategory?.name ?: "",
                onValueChange = {},
                readOnly = true,
                placeholder = {
                    Text(
                        text = "Categoria",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AddBusiness,
                        contentDescription = "Person Icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                ),
                shape = RoundedCornerShape(percent = 50),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item.name) },
                        onClick = {
                            onItemSelected(selectedCategoryId)
                            expanded = false
                        },
                    )
                }
            }
        }
    }
}

// Example usage
@Composable
fun ExampleScreen(productState: ProductState, viewModel: ProductViewModel) {
    val items = remember {
        listOf(
            Category(1, "Comidas Rapidas"),
            Category(2, "Bebidas frias"),
            Category(3, "Bebidas calientes")
        )
    }

    DropdownWithIdAndName(
        items = items,
        selectedCategoryId = productState.categoryId.value,
        onItemSelected = { productState.categoryId.value = it }
    )
}

@Composable
fun ButtonAddProduct(categoryName: String, image: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(120.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(20.dp)) // Evita la forma redonda del botón
            .background(Color(0xFFC8E1DA)), // Aplica color de fondo
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC8E1DA)), // Evita color por defecto
        contentPadding = PaddingValues(0.dp) // Elimina padding interno del botón
    ) {
        Box(
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
                .padding(6.dp)
                .background(Color(0xFFC8E1DA), shape = RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = categoryName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = image), // Reemplaza con tu recurso
                    contentDescription = categoryName,
                    modifier = Modifier
                        .size(90.dp)
                )
            }
        }
    }
}

@Composable
fun DialogWithImage(
    onDismissRequest: () -> Unit,
    state: ProductState,
    productViewModel: ProductViewModel,
    name: String,
    price: Long,
    id: Int,
    categoryId: Int,
    navHostController: NavHostController
) {
    Dialog( onDismissRequest = { onDismissRequest() } ) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(150.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        state.id.value = id
                        state.name.value = name
                        state.price.value = price
                        state.categoryId.value = categoryId
                        productViewModel.deleteProduct()
                        onDismissRequest()
                    }
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar",
                            tint = MaterialTheme.colorScheme.error
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Eliminar",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                IconButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        navHostController.navigate(Routes.EditProductScreen.route)
                    }
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar",
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Editar",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun TableDialog(
    onDismiss: () -> Unit,
    tableViewModel: TableViewModel,
    navHostController: NavHostController,
) {
    var tableNumber by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var checkTable by remember { mutableStateOf(false) } // Estado para activar la verificación

    // Ejecutar la verificación cuando `checkTable` cambie a true
    LaunchedEffect(checkTable) {
        if (checkTable) {
            val exists = withContext(Dispatchers.IO) { tableViewModel.checkTable(tableNumber) }
            if (!exists) {
                errorMessage = "Esta mesa ya está registrada"
            } else {
                navHostController.navigate(Routes.CategoriesScreen.createRoute(tableNumber.toInt()))
            }
            checkTable = false // Resetear el estado después de la verificación
        }
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Ingrese el número de la mesa") },
        text = {
            Column {
                OutlinedTextField(
                    value = tableNumber,
                    onValueChange = { tableNumber = it },
                    label = { Text("Número de mesa") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = errorMessage.isNotEmpty()
                )
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                if (tableNumber.isBlank()) {
                    errorMessage = "Ingrese un número válido"
                    return@Button
                }
                checkTable = true // Activar la verificación en `LaunchedEffect`
            }) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun AlertDialogRequest(
    onDismiss: () -> Unit,
    title: String,
    onClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = title) },
        confirmButton = {
            Button(onClick = onClick) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancelar")
            }
        }
    )
}
