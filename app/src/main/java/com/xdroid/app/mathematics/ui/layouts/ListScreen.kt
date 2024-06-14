package com.xdroid.app.mathematics.ui.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.xdroid.app.mathematics.cmodel.ItemModel
import com.xdroid.app.mathematics.cmodel.MyItems
import com.xdroid.app.mathematics.data.UrlName
import com.xdroid.app.mathematics.ui.screens.ScreenName
import com.xdroid.app.mathematics.ui.theme.background
import com.xdroid.app.mathematics.ui.theme.boxColor
import com.xdroid.app.mathematics.ui.theme.title
import com.xdroid.app.mathematics.utils.enums.Resource
import com.xdroid.app.mathematics.utils.enums.Status
import com.xdroid.app.mathematics.utils.helpers.DynamicResponse
import com.xdroid.app.mathematics.utils.helpers.ListItems
import com.xdroid.app.mathematics.utils.vm.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
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
        Toolbar(navController,"Multiplication Table")
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 5.dp)
        ) {
            ActionsItemList(
                items = ListItems.getMultiplicationList(),
                navController = navController
            )

        }

    }

}

@Composable
fun TitleCompose(titleText: String = "") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = titleText, color = Color.White, style = title)
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Book,
                contentDescription = "Book",
                tint = Color.White
            )
        }
    }
}

@Composable
fun ActionsItemList(
    items: List<String>?,
    navController: NavController
) {
    val count = 2
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(4),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        if (items != null)
            items(items.size) { index ->
                ActionItems(items[index], navController)
            }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ActionItems(
    item: String,
    navController: NavController,
    modifier: Modifier = Modifier.fillMaxWidth()
) {

    Box(
        modifier = modifier
            .padding(8.dp)
            .background(boxColor, shape = RoundedCornerShape(10.dp))
            .clickable {
                val screen = ScreenName.Detail
                navController.navigate(
                    ScreenName.detailRoute(
                        screen,
                        item
                    )
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
            Text(text = item, color = Color.White)
            Spacer(modifier = Modifier.height(5.dp))
        }


    }
}
