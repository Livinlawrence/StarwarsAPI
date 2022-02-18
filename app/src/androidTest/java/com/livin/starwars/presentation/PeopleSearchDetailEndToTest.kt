package com.livin.starwars.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.google.common.truth.Truth.assertThat
import com.livin.starwars.Utils.waitForSomeTime
import com.livin.starwars.di.AppModule
import com.livin.starwars.presentation.people_details.PeopleDetailsScreen
import com.livin.starwars.presentation.people_search.PeopleSearchScreen
import com.livin.starwars.presentation.ui.theme.StarwarsAppTheme
import com.livin.starwars.utils.Screen
import com.livin.starwars.utils.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.navigation.compose.rememberNavController as rememberNavController1

@HiltAndroidTest
@UninstallModules(AppModule::class)
class PeopleSearchDetailEndToTest {

    private lateinit var navController: NavHostController

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            StarwarsAppTheme {
                navController = rememberNavController1()
                NavHost(
                    navController = navController,
                    startDestination = Screen.PeopleSearchScreen.route
                ) {
                    composable(route = Screen.PeopleSearchScreen.route) {
                        PeopleSearchScreen(navController = navController)
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
                            }
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


    @Test
    fun performClick_Navigation() {
        val inputText = "a"
        val testData = "Anakin Skywalker"
        with(composeRule){
            onNodeWithTag(TestTags.PEOPLE_SEARCH_TEXT_FIELD).performTextInput(inputText)
            waitForSomeTime(5000L)
            onNodeWithText(testData).performClick()
            val route = navController.currentBackStackEntry?.destination?.route
            assertThat(route).isEqualTo(Screen.PeopleDetailsScreen.route +
                    "?peopleId={peopleId}")
        }
    }
}

