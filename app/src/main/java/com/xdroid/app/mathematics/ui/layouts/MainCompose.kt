package com.xdroid.app.mathematics.ui.layouts

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xdroid.app.mathematics.App
import com.xdroid.app.mathematics.ui.screens.ScreenName
import com.xdroid.app.mathematics.utils.helpers.PreferenceHelper


@Composable
fun MyApp() {
    val navController = rememberNavController()
    App.preferenceHelper = PreferenceHelper(LocalContext.current)
    /*All navigation are included here and all navigation are done from here.*/
    /*Add new route to app for navigation*/

    NavHost(navController, startDestination = ScreenName.NavigationScreen) {
        composable(ScreenName.Home) {
            HomeScreen(navController)
        }
        composable(ScreenName.NavigationScreen) {
            NavigationScreen(navController)
        }
        composable(ScreenName.SimpleInterest) {
            SimpleInterestScreen(navController)
        }
        composable(ScreenName.TimeConverter) {
            TimeConverterScreen(navController)
        }
        composable(ScreenName.Detail + "?table={table}") { backstack ->
            val table = backstack.arguments?.getString("table") ?: ""
            TableScreen(table, navController)
        }

    }
}