package com.xdroid.app.mathematics.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.xdroid.app.mathematics.ui.theme.black
import com.xdroid.app.mathematics.ui.theme.grey
import com.xdroid.app.mathematics.ui.theme.regular
import com.xdroid.app.mathematics.ui.theme.white


@Composable
fun DropdownMenus(
    label: String = "Dropdown",
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    errorMessage: String?,
    modifier: Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        Text(
            text = label,
            color = white,
            style = regular
        )
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = grey, shape = RoundedCornerShape(10.dp))
                .clickable(onClick = { expanded = true })
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Text(text = selectedOption, style = regular)
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = white
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            modifier = modifier
                .background(color = black)
                .padding(horizontal = 10.dp),
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    text = {
                        Text(
                            option,
                            style = regular,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 15.dp)
                        )
                    }

                )
            }
        }
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
}

@Composable
fun DropdownExample(modifier: Modifier = Modifier.fillMaxWidth()): String {
    var selectedOption by remember { mutableStateOf("Option 1") }

    val options = listOf("Option 1", "Option 2", "Option 3")

    DropdownMenus(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = { option ->
            selectedOption = option

        },
        errorMessage = "",
        label = "Label",
        modifier = modifier
    )
    return selectedOption
}

@Composable
fun DynamicDropDown(
    label: String = "Label",
    modifier: Modifier = Modifier.fillMaxWidth(),
    errorMessage: String? = "",
    data: List<String>,
    value: String? = null,
    onOptionSelected: (String) -> Unit,
): String {
    var selectedOption by remember { mutableStateOf(value ?: data[0]) }

    DropdownMenus(
        options = data,
        selectedOption = selectedOption,
        onOptionSelected = { option ->
            selectedOption = option
            onOptionSelected(selectedOption)
        },
        errorMessage = errorMessage,
        label = label,
        modifier = modifier
    )
    return selectedOption
}