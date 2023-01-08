package com.basta.counter.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.basta.counter.screen.EnterDatePage
import com.basta.counter.screen.HomePage

@ExperimentalFoundationApi
fun NavGraphBuilder.buildTheGraph(navController: NavController) {
    composable(Directions.home.name) {
        HomePage(navController)
    }
    composable(Directions.enterDate.name) {
        EnterDatePage(navController)
    }

}