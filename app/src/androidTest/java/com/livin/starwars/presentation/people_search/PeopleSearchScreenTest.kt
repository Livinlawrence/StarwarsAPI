package com.livin.starwars.presentation.people_search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.livin.starwars.Utils.waitForSomeTime
import com.livin.starwars.di.AppModule
import com.livin.starwars.presentation.MainActivity
import com.livin.starwars.presentation.ui.theme.StarwarsAppTheme
import com.livin.starwars.utils.Screen
import com.livin.starwars.utils.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class PeopleSearchScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            StarwarsAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.PeopleSearchScreen.route
                ) {
                    composable(route = Screen.PeopleSearchScreen.route) {
                        PeopleSearchScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun peopleSearchField_isVisible() {
        composeRule.onNodeWithTag(TestTags.PEOPLE_SEARCH_TEXT_FIELD).assertIsDisplayed()
    }

    @Test
    fun setSearchValue_Check() {
        val inputText = "a"
        composeRule.onNodeWithTag(TestTags.PEOPLE_SEARCH_TEXT_FIELD).performTextInput(inputText)
        composeRule.onNodeWithTag(TestTags.PEOPLE_SEARCH_TEXT_FIELD).assert(hasText(inputText))
    }

    @Test
    fun performList_isVisible() {
        val inputText = "a"
        composeRule.onNodeWithTag(TestTags.PEOPLE_SEARCH_TEXT_FIELD).performTextInput(inputText)
        waitForSomeTime(3000L)
        composeRule.onNodeWithTag(TestTags.PEOPLE_SEARCH_LIST).assertIsDisplayed()
    }
}