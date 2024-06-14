package com.xdroid.app.mathematics.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.xdroid.app.mathematics.ui.theme.*
import com.xdroid.app.mathematics.utils.constants.FieldConstantText.password
import com.xdroid.app.mathematics.utils.constants.FieldConstantText.username


@Composable
fun PasswordField(
    label: String = password,
    errorMessage: String? = "",
    value: String? = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions()
): String {
    var password by remember { mutableStateOf(value ?: "") }
    var passwordVisible by remember { mutableStateOf(false) }
    Column(
    ) {
        Text(
            text = label,
            style = regular
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = password,
            textStyle = regular,
            onValueChange = { newText ->
                password = newText
            },
            placeholder = { Text(text = label, style = regular) },
            shape = RoundedCornerShape(percent = 20),
            visualTransformation = if (passwordVisible) {

                VisualTransformation.None

            } else {

                PasswordVisualTransformation()

            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = 1,
            trailingIcon = {
                if (passwordVisible) {
                    IconButton(onClick = { passwordVisible = false }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "hide_password",
                            tint = white,
                        )
                    }
                } else {
                    IconButton(
                        onClick = { passwordVisible = true }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "hide_password",
                            tint = white,
                        )
                    }
                }
            }
        )

        if (!errorMessage.isNullOrEmpty()) {

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = errorMessage,
                color = errorColor,
                style = regular
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
    return password
}

@Composable
fun InputTextField(
    label: String = username,
    errorMessage: String? = "",
    value: String? = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions(),
    maxLength: Int = 0
): String {
    var username by remember { mutableStateOf(value ?: "") }
    Column(
    ) {

        Text(
            text = label,
            style = labelStyle
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = username,
            onValueChange = {
                if (maxLength == 0)
                    username = it
                else {
                    if (it.length <= maxLength) username = it
                }
            },
            placeholder = { Text(label, style = regular, color = grey) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            textStyle = regular,
            maxLines = 1
        )

        if (!errorMessage.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = errorMessage,
                color = Color.Red,
                style = regular
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
    return username
}

@Composable
fun InputTextField3(
    label: String = username,
    errorMessage: String? = "",
    value: String? = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions(),
    maxLength: Int = 0,
    placeHolder: String = "10",
): String {
    var username by remember { mutableStateOf(value ?: "") }
    Column(
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = labelStyle
            )
            Spacer(modifier = Modifier.width(15.dp))
            OutlinedTextField(
                value = username,
                onValueChange = {
                    if (maxLength == 0)
                        username = it
                    else {
                        if (it.length <= maxLength) username = it
                    }
                },
                placeholder = { Text(placeHolder, style = regular, color = grey) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                textStyle = regular,
                maxLines = 1
            )


        }
        if (!errorMessage.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = errorMessage,
                color = Color.Red,
                style = regular,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
    return username
}


@Composable
fun InputTextField2(
    label: String = username,
    errorMessage: String? ="",
    value: String? = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions(),
    maxLength: Int = 0,
    onValueChange: (String) -> Unit
): String {
    var username by remember { mutableStateOf(value ?: "") }
    Column(
    ) {

//        Text(
//            text = label,
//            style = TextStyles.latoRegular16
//        )
//        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = username,
            onValueChange = {
                if (maxLength == 0)
                    username = it
                else {
                    if (it.length <= maxLength) username = it
                }
                onValueChange(it)
            },
//            placeholder = { Text(label, style = TextStyles.latoRegular16) },
            label = { Text(label, style = regular) },

            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            textStyle = regular,
            maxLines = 1
        )

        if (!errorMessage.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = errorMessage,
                color = Color.Red,
                style = regular
            )
        }

        Spacer(modifier = Modifier.height(5.dp))
    }
    return username
}

@Composable
fun DateTextField(
    label: String = username,
    errorMessage: String?,
    value: String? = ""
): String {
    val username = remember { mutableStateOf(value ?: "") }

    val showCalender = remember { mutableStateOf(false) }
    Column(
    ) {

        Text(
            text = label,
            style = regular
        )
        Spacer(modifier = Modifier.height(5.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable { showCalender.value = true }) {
            OutlinedTextField(
                value = username.value,
                onValueChange = { username.value = it },
                readOnly = true,
                placeholder = { Text("2024-10-10", style = regular) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = regular,
                enabled = false,
                maxLines = 1
            )
        }


        if (!errorMessage.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = errorMessage,
                color = Color.Red,
                style = regular
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }

//    ShowDatePickerDialog(showDialog = showCalender, dateSelected = username, mDate = username.value)
    return username.value
}
/*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextFieldRow(
    label: String = username,
    errorMessage: String?,
    value: String? = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions(),
    maxLength: Int = 0
): String {
    var username by remember { mutableStateOf(value ?: "") }


    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "+977",
                style = regular
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .height(30.dp)
                    .width(2.dp)
                    .background(black)
            )
            TextField(
                value = username,
                onValueChange = {
                    if (maxLength == 0)
                        username = it
                    else {
                        if (it.length <= maxLength) username = it
                    }
                },
                placeholder = { Text(label, style = regular) },
                singleLine = true,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                textStyle = regular,
                maxLines = 1,
                colors = TextFieldColors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    disabledContainerColor = Color.Gray,
                    disabledIndicatorColor = Color.Gray,
                    disabledLabelColor = Color.Gray,
                    disabledLeadingIconColor = Color.Gray,
                    disabledPlaceholderColor = Color.Gray,
                    disabledPrefixColor = Color.Gray,
                    disabledSuffixColor = Color.Gray,
                    disabledSupportingTextColor = Color.Gray,
                    disabledTextColor = Color.Gray,
                    disabledTrailingIconColor = Color.Gray,
                    errorContainerColor = Color.Transparent,
                    errorCursorColor = Color.Red,
                    errorIndicatorColor = Color.Red,
                    errorLabelColor = Color.Red,
                    errorLeadingIconColor = Color.Red,
                    errorPlaceholderColor = Color.Transparent,
                    errorPrefixColor = Color.Transparent,
                    errorSuffixColor = Color.Transparent,
                    errorSupportingTextColor = Color.Transparent,
                    errorTextColor = Color.Transparent,
                    errorTrailingIconColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    focusedLeadingIconColor = Color.Transparent,
                    focusedPlaceholderColor = Color.Transparent,
                    unfocusedTrailingIconColor = Color.Transparent,
                    focusedPrefixColor = Color.Transparent,
                    focusedSuffixColor = Color.Transparent,
                    focusedSupportingTextColor = Color.Transparent,
                    focusedTextColor = Color.Transparent,
                    focusedTrailingIconColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent,
                    unfocusedLeadingIconColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color.Transparent,
                    unfocusedPrefixColor = Color.Transparent,
                    unfocusedSuffixColor = Color.Transparent,
                    unfocusedSupportingTextColor = Color.Transparent,
                    unfocusedTextColor = Color.Transparent,
                    textSelectionColors = TextSelectionColors(
                        handleColor = Color.Transparent,
                        backgroundColor = Color.Transparent
                    )
                )

            )


        }

        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
                .background(Color.White)
        )
        if (!errorMessage.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = errorMessage,
                color = Color.Red,
                style = regular,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

    }
    return username
}

*/
/*

@Composable
fun MyTextField(
    label: String = username,
    errorMessage: String?,
    value: String? = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions(),
    focusedContainerColor: Color = Color.Transparent,
    unfocusedContainerColor: Color = Color.Transparent
) {

    var username by remember { mutableStateOf(value ?: "") }

    TextField(
        value = username,
        onValueChange = { username = it },
        placeholder = { Text(label, style = regular) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        textStyle = regular,
        maxLines = 1,
        colors = TextFieldColors(
            focusedContainerColor = focusedContainerColor,
            unfocusedContainerColor = unfocusedContainerColor,
            cursorColor = Color.Black,
            disabledContainerColor = Color.Gray,
            disabledIndicatorColor = Color.Gray,
            disabledLabelColor = Color.Gray,
            disabledLeadingIconColor = Color.Gray,
            disabledPlaceholderColor = Color.Gray,
            disabledPrefixColor = Color.Gray,
            disabledSuffixColor = Color.Gray,
            disabledSupportingTextColor = Color.Gray,
            disabledTextColor = Color.Gray,
            disabledTrailingIconColor = Color.Gray,
            errorContainerColor = Color.Transparent,
            errorCursorColor = Color.Red,
            errorIndicatorColor = Color.Red,
            errorLabelColor = Color.Red,
            errorLeadingIconColor = Color.Red,
            errorPlaceholderColor = Color.Transparent,
            errorPrefixColor = Color.Transparent,
            errorSuffixColor = Color.Transparent,
            errorSupportingTextColor = Color.Transparent,
            errorTextColor = Color.Transparent,
            errorTrailingIconColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Transparent,
            focusedLeadingIconColor = Color.Transparent,
            focusedPlaceholderColor = Color.Transparent,
            unfocusedTrailingIconColor = Color.Transparent,
            focusedPrefixColor = Color.Transparent,
            focusedSuffixColor = Color.Transparent,
            focusedSupportingTextColor = Color.Transparent,
            focusedTextColor = Color.Transparent,
            focusedTrailingIconColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedLabelColor = Color.Transparent,
            unfocusedLeadingIconColor = Color.Transparent,
            unfocusedPlaceholderColor = Color.Transparent,
            unfocusedPrefixColor = Color.Transparent,
            unfocusedSuffixColor = Color.Transparent,
            unfocusedSupportingTextColor = Color.Transparent,
            unfocusedTextColor = Color.Transparent,
            textSelectionColors = TextSelectionColors(
                handleColor = Color.Gray,
                backgroundColor = Color.Gray
            )
        )

    )
}
*/
/*

@Composable
fun MyTextFieldValue(
    label: String = username,
    onValueChange: (String) -> Unit,
    value: String? = "",
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Next
    ),
    keyboardActions: KeyboardActions = KeyboardActions(),
    focusedContainerColor: Color = Color.Transparent,
    unfocusedContainerColor: Color = Color.Transparent
) {

    var username by remember { mutableStateOf(value ?: "") }

    TextField(
        value = username,
        modifier = modifier,
        onValueChange = {
            username = it
            onValueChange(it)
        },
        placeholder = { Text(label, style = regular) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        textStyle = regular,
        maxLines = 1,
        colors = TextFieldColors(
            focusedContainerColor = focusedContainerColor,
            unfocusedContainerColor = unfocusedContainerColor,
            cursorColor = Color.Black,
            disabledContainerColor = Color.Gray,
            disabledIndicatorColor = Color.Gray,
            disabledLabelColor = Color.Gray,
            disabledLeadingIconColor = Color.Gray,
            disabledPlaceholderColor = Color.Gray,
            disabledPrefixColor = Color.Gray,
            disabledSuffixColor = Color.Gray,
            disabledSupportingTextColor = Color.Gray,
            disabledTextColor = Color.Gray,
            disabledTrailingIconColor = Color.Gray,
            errorContainerColor = Color.Transparent,
            errorCursorColor = Color.Red,
            errorIndicatorColor = Color.Red,
            errorLabelColor = Color.Red,
            errorLeadingIconColor = Color.Red,
            errorPlaceholderColor = Color.Transparent,
            errorPrefixColor = Color.Transparent,
            errorSuffixColor = Color.Transparent,
            errorSupportingTextColor = Color.Transparent,
            errorTextColor = Color.Transparent,
            errorTrailingIconColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Transparent,
            focusedLeadingIconColor = Color.Transparent,
            focusedPlaceholderColor = Color.Transparent,
            unfocusedTrailingIconColor = Color.Transparent,
            focusedPrefixColor = Color.Transparent,
            focusedSuffixColor = Color.Transparent,
            focusedSupportingTextColor = Color.Transparent,
            focusedTextColor = Color.Transparent,
            focusedTrailingIconColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedLabelColor = Color.Transparent,
            unfocusedLeadingIconColor = Color.Transparent,
            unfocusedPlaceholderColor = Color.Transparent,
            unfocusedPrefixColor = Color.Transparent,
            unfocusedSuffixColor = Color.Transparent,
            unfocusedSupportingTextColor = Color.Transparent,
            unfocusedTextColor = Color.Transparent,
            textSelectionColors = TextSelectionColors(
                handleColor = Color.Gray,
                backgroundColor = Color.Gray
            )
        )

    )
}
*/

@Composable
fun TextIcon(icons: ImageVector, text: String, textColor: Color = colorPrimary) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icons, contentDescription = "Icon", tint = colorPrimary)
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = text, style = regular, color = textColor)

    }
}

@Composable
fun Pricing(price: String, text: String) {
    Row(verticalAlignment = Alignment.Top) {
        Text(text = price, style = regular, color = black)
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = text, style = regular, color = white)

    }
}

@Composable
fun TitleText(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, style = regular, color = black)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "View all", style = regular, color = colorPrimary)
            Icon(
                Icons.AutoMirrored.Default.NavigateNext,
                contentDescription = "Icon",
                tint = colorPrimary
            )

        }

    }
}

@Composable
fun SelectionText(text: String) {

    Box(
        modifier = Modifier
            .background(color = colorPrimary, shape = RoundedCornerShape(10.dp))
    ) {
        Text(
            text = text,
            color = white,
            style = regular,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )

    }

}

@Composable
fun IconBtn(icons: ImageVector) {

    Box(
        modifier = Modifier
            .background(color = colorPrimary, shape = RoundedCornerShape(10.dp))
    ) {
        Icon(icons, contentDescription = "Icon", tint = white, modifier = Modifier.padding(10.dp))

    }

}

@Composable
fun InfoText(icons: ImageVector, text: String, tint: Color = black) {

    Box(
        modifier = androidx.compose.ui.Modifier.background(
            white,
            shape = RoundedCornerShape(8.dp)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Icon(icons, contentDescription = "Icon", tint = tint)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = text, style = regular, color = black)

        }
    }
}


@Composable
fun ClickableBoxWithText(text: String, click: Boolean, onClick: () -> Unit) {


    Box(
        modifier = Modifier
            .padding(4.dp)
            .width(150.dp)
            .clickable {
                onClick()
            }
            .background(
                if (click) colorPrimary else white,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            modifier = Modifier.padding(8.dp),
            style = regular,
            textAlign = TextAlign.Center
        )
    }

}


@Composable
fun BoxContainer(
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = androidx.compose.ui.Modifier.background(
            white,
            shape = RoundedCornerShape(10.dp)
        )
    ) {
        content

    }
}

@Composable
fun BoxContainer(
    icons: ImageVector = Icons.Filled.Bookmark, tint: Color = black, modifier: Modifier = Modifier
) {
    Box(modifier = modifier.background(white, shape = RoundedCornerShape(10.dp))) {
        Icon(
            icons,
            contentDescription = "Icon",
            tint = tint,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(5.dp)
        )

    }
}

@Composable
fun EventInfoContainer(
    icons: ImageVector = Icons.Filled.Bookmark, tint: Color = black, title: String, desc: String
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = androidx.compose.ui.Modifier.background(
                white,
                shape = RoundedCornerShape(12.dp)
            )
        ) {
            Icon(
                icons,
                contentDescription = "Icon",
                tint = tint,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp)
            )

        }
        Spacer(modifier = Modifier.width(15.dp))
        Column {
            Text(text = title, style = regular, color = black)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = desc, style = regular, color = white)

        }

    }
}

@Composable
fun EventDatePickers(

) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Outlined.CalendarMonth,
                contentDescription = "Icon",
                tint = white,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(text = "Choose Date", style = regular, color = white)

        }
        Spacer(modifier = Modifier.width(15.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Filled.AccountCircle,
                contentDescription = "Icon",
                tint = white,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(text = "1", style = regular, color = white)

        }

    }
}