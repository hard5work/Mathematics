package com.xdroid.app.mathematics.ui.layouts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.xdroid.app.mathematics.ui.components.ButtonComponent
import com.xdroid.app.mathematics.ui.components.DynamicDropDown
import com.xdroid.app.mathematics.ui.components.InputTextField2
import com.xdroid.app.mathematics.ui.components.InputTextField3
import com.xdroid.app.mathematics.ui.theme.answer
import com.xdroid.app.mathematics.ui.theme.errorText
import com.xdroid.app.mathematics.ui.theme.note
import com.xdroid.app.mathematics.ui.theme.white
import com.xdroid.app.mathematics.utils.constants.FieldConstantText
import com.xdroid.app.mathematics.utils.enums.Resource
import com.xdroid.app.mathematics.utils.enums.Status
import com.xdroid.app.mathematics.utils.helpers.ListItems
import com.xdroid.app.mathematics.utils.vm.HomeViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun TimeConverterScreen(
    navController: NavController
//    dashVM: DashVM = koinInject(),
//    navigateToDetail: (WatchlistMovie) -> Unit
) {
    val homeViewModel: HomeViewModel = koinViewModel()

    val state by homeViewModel.myResult.collectAsState(Resource.idle())

    var showView by remember { mutableStateOf(false) }
    var isloading by remember { mutableStateOf(false) }
    var principal by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var rate by remember { mutableStateOf("") }
    var interest by remember { mutableStateOf("") }
    var selectedItem1 by remember { mutableStateOf("") }
    var item1 by remember { mutableStateOf("") }
    var selectedItem2 by remember { mutableStateOf("") }
    var item2 by remember { mutableStateOf("") }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Toolbar(text = "Time Converter", navController = navController)
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 5.dp, top = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {

                Spacer(modifier = Modifier.height(5.dp))
                Text(text = item2, style = answer, color = white)
                Spacer(modifier = Modifier.height(10.dp))
                Column() {
                    item1 = InputTextField2(
                        value = item1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = "From",
                        onValueChange = {
                            homeViewModel.calculateTime(it, selectedItem1, selectedItem2)
//                            homeViewModel.convertTime(it,selectedItem1,selectedItem2)
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    DynamicDropDown(data = ListItems.getWeeks(),
                        label = "Time", onOptionSelected = {
                            selectedItem1 = it
                            homeViewModel.calculateTime(item1, selectedItem1, selectedItem2)
//                            homeViewModel.convertTime(item1,selectedItem1,selectedItem2)
                        })

                }

                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.ArrowUpward, contentDescription = "", tint = white)
                    Icon(Icons.Default.ArrowDownward, contentDescription = "", tint = white)
                }
                Spacer(modifier = Modifier.height(5.dp))
                Column() {

                    Spacer(modifier = Modifier.height(5.dp))
                    DynamicDropDown(data = ListItems.getWeeks(),
                        label = "Time", onOptionSelected = {
                            selectedItem2 = it

//                            homeViewModel.convertTime(item1,selectedItem1,selectedItem2)
                            homeViewModel.calculateTime(item1, selectedItem1, selectedItem2)
                        })

                    /*  item2 = InputTextField2(
                          value = item2,
                          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                          label = "To"
                      )*/

                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            when (state.status) {
                Status.ERROR -> {
                    Text(text = "${state.message}", style = errorText)

                }
                Status.IDLE -> {

                }
                Status.LOADING -> {

                }
                Status.SUCCESS -> {
                    item2 = "Your answer is ${state.data}"

                }
            }
        }

    }

}

