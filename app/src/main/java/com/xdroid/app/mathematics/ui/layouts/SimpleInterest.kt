package com.xdroid.app.mathematics.ui.layouts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.xdroid.app.mathematics.cmodel.ItemModel
import com.xdroid.app.mathematics.ui.components.ButtonComponent
import com.xdroid.app.mathematics.ui.components.InputTextField
import com.xdroid.app.mathematics.ui.components.InputTextField3
import com.xdroid.app.mathematics.ui.theme.*
import com.xdroid.app.mathematics.utils.constants.FieldConstantText.notes
import com.xdroid.app.mathematics.utils.enums.Resource
import com.xdroid.app.mathematics.utils.enums.Status
import com.xdroid.app.mathematics.utils.helpers.DebugMode
import com.xdroid.app.mathematics.utils.helpers.ListItems
import com.xdroid.app.mathematics.utils.vm.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SimpleInterestScreen(
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



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Toolbar(text = "Simple Interest", navController = navController)
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 5.dp, top = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = notes, style = note, color = white)
            Spacer(modifier = Modifier.height(10.dp))
            principal = InputTextField3(
                label = "Principal (P)",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = principal
            )
            Spacer(modifier = Modifier.height(10.dp))
            rate = InputTextField3(
                label = "Annual Rate (R)",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = rate
            )
            Spacer(modifier = Modifier.height(10.dp))
            time = InputTextField3(
                label = "Time in Years (T)",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = time
            )
            Spacer(modifier = Modifier.height(10.dp))
            interest = InputTextField3(
                label = "Simple Interest (I)",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = interest
            )
            Spacer(modifier = Modifier.height(10.dp))
            if (isloading) {
                CircularProgressIndicator()
            } else {
                ButtonComponent(label = "Find Value", onClick = {
                    homeViewModel.calculateSimpleInterest(
                        principal = principal,
                        rate = rate,
                        time = time,
                        interest = interest
                    )

                })
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
                    Text(text = "Your answer is ${state.data}", style = answer, color = white)
                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }

    }

}

