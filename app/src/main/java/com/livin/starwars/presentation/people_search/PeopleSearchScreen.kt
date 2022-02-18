package com.livin.starwars.presentation.people_search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.livin.starwars.common.UiEvent
import com.livin.starwars.presentation.ui.PeopleListItem
import com.livin.starwars.utils.Screen
import com.livin.starwars.utils.TestTags.PEOPLE_SEARCH_LIST
import com.livin.starwars.utils.TestTags.PEOPLE_SEARCH_TEXT_FIELD
import kotlinx.coroutines.flow.collectLatest


@Composable
fun PeopleSearchScreen(
    navController: NavController,
    viewModel: PeopleSearchViewModel = hiltViewModel()
) {

    val peopleSearchState = viewModel.peopleSearchState.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(message = it.message)
                }
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                TextField(
                    value = viewModel.searchFieldState.value,
                    onValueChange = viewModel::searchWord,
                    modifier = Modifier.fillMaxWidth().
                    testTag(PEOPLE_SEARCH_TEXT_FIELD),
                    placeholder = {
                        Text(text = "Search here")
                    }
                )
                if (peopleSearchState.data.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(20.dp))
                    LazyColumn(modifier = Modifier.fillMaxWidth().
                    testTag(PEOPLE_SEARCH_LIST)) {
                        items(peopleSearchState.data.size) { i ->
                            val item = peopleSearchState.data[i]
                            if (i > 0) {
                                Spacer(modifier = Modifier.height(20.dp))
                            }

                            PeopleListItem(people = item, modifier = Modifier.clickable {
                                navController.navigate(
                                    Screen.PeopleDetailsScreen.route +
                                            "?peopleId=${item.peopleId}"
                                )
                            })

                            if (i < peopleSearchState.data.size - 1) {
                                Divider()
                            }
                        }
                    }
                }
            }
        }

        if (peopleSearchState.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }
}