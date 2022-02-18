package com.livin.starwars.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.livin.starwars.presentation.people_details.PeopleDetailsScreen
import com.livin.starwars.presentation.people_search.PeopleSearchScreen
import com.livin.starwars.presentation.ui.theme.StarwarsAppTheme
import com.livin.starwars.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarwarsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PeopleSearchScreen.route
                    ) {
                        composable(route = Screen.PeopleSearchScreen.route) {
                            PeopleSearchScreen(
                                navController = navController
                            )
                        }
                        composable(
                            route = Screen.PeopleDetailsScreen.route +
                                    "?peopleId={peopleId}",
                            arguments = listOf(
                                navArgument(
                                    name = "peopleId"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                },
                            )
                        ) {
                            PeopleDetailsScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}