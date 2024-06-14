package com.xdroid.app.mathematics.ui.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.xdroid.app.mathematics.cmodel.ItemModel
import com.xdroid.app.mathematics.ui.screens.ScreenName
import com.xdroid.app.mathematics.ui.theme.boxColor
import com.xdroid.app.mathematics.ui.theme.title
import com.xdroid.app.mathematics.utils.enums.Resource
import com.xdroid.app.mathematics.utils.helpers.ListItems
import com.xdroid.app.mathematics.utils.vm.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationScreen(
    navController: NavController
//    dashVM: DashVM = koinInject(),
//    navigateToDetail: (WatchlistMovie) -> Unit
) {
    val homeViewModel: HomeViewModel = koinViewModel()

    val states by homeViewModel.imageResponse.collectAsState(initial = Resource.idle())

    LaunchedEffect(Unit) {
//        homeViewModel.getAllImage()
    }
    val itemModel = remember { mutableStateOf(ItemModel()) }
    var showView by remember { mutableStateOf(false) }
    var showAlert by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        Toolbar("Home Screen")
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 5.dp)
        ) {
            NavList(
                items = ListItems.getMenuList(),
                navController = navController
            )

        }

    }

}

@Composable
fun Toolbar(text: String = "") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, color = Color.White, style = title)
    }
}

@Composable
fun Toolbar(navController: NavController, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            navController.navigateUp()
        }) {
            Icon(
                Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "BackArrow",
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.width(5.dp))
        Text(text = text, style = title, color = Color.White)

    }
}

@Composable
fun NavList(
    items: List<String>?,
    navController: NavController
) {
    val count = 2
    LazyColumn {

        if (items != null)
            items(items.size) { index ->
                NavigationItem(items[index], navController)
            }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NavigationItem(
    item: String,
    navController: NavController,
    modifier: Modifier = Modifier.fillMaxWidth()
) {

    Box(
        modifier = modifier
            .padding(8.dp)
            .background(boxColor, shape = RoundedCornerShape(10.dp))
            .clickable {
                val screen = when (item) {
                    "Multiplication" -> {
                        ScreenName.Home
                    }
                    "Simple Interest" -> {
                        ScreenName.SimpleInterest
                    }
                    "Time Converter" -> {
                        ScreenName.TimeConverter
                    }
                    else -> {
                        ""
                    }
                }
                navController.navigate(
                    screen
                )
            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = item, color = Color.White, style = title)
            Spacer(modifier = Modifier.height(5.dp))
        }


    }
}
