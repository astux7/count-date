package com.astux7.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.astux7.counter.navigation.Directions
import com.astux7.counter.navigation.buildTheGraph
import com.astux7.counter.ui.theme.KickoffTheme


class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KickoffTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Directions.home.name
                    ) {
                        buildTheGraph(navController)
                    }
                }
            }
        }
    }
}
