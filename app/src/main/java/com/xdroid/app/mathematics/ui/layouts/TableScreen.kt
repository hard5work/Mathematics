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
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.xdroid.app.mathematics.ui.screens.ScreenName
import com.xdroid.app.mathematics.ui.theme.boxColor
import com.xdroid.app.mathematics.ui.theme.tableItem
import com.xdroid.app.mathematics.ui.theme.title
import com.xdroid.app.mathematics.utils.helpers.ListItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TableScreen(
    table: String,
    navController: NavController
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically) {
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
            Text(text = "Multiplication table of $table", style = title, color = Color.White)

        }
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            MultiList(items = ListItems.getMultiplicationList(table.toInt()))

        }
    }
}


@Composable
fun MultiList(
    items: List<String>?
) {
    LazyColumn {

        if (items != null)
            items(items.size) { index ->
                MultiTable(items[index])
            }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MultiTable(
    item: String,
    modifier: Modifier = Modifier.fillMaxWidth()
) {

    Box(
        modifier = modifier
            .padding(8.dp)
            .background(boxColor, shape = RoundedCornerShape(10.dp)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp, horizontal = 15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = item, color = Color.White, style = tableItem)
            Spacer(modifier = Modifier.height(5.dp))
        }


    }
}
