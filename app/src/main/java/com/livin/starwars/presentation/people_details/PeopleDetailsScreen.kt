package com.livin.starwars.presentation.people_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.livin.starwars.R
import com.livin.starwars.common.UiEvent
import com.livin.starwars.presentation.ui.FilmListItem
import com.livin.starwars.utils.TestTags
import kotlinx.coroutines.flow.collectLatest


@Composable
fun PeopleDetailsScreen(
    navController: NavController,
    viewModel: PeopleDetailsViewModel = hiltViewModel()
) {
    val peopleSearchState = viewModel.peopleDetailsState.value
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
        peopleSearchState.peopleDetails?.let { peopleDetails ->
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

                    Text(text = peopleDetails.name, style = MaterialTheme.typography.h4,
                        modifier = Modifier.testTag(
                            TestTags.PEOPLE_TITLE_TEXT_FIELD
                        ))
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.label_birth_year),
                            style = MaterialTheme.typography.h6
                        )
                        Text(text = peopleDetails.birthYear, style = MaterialTheme.typography.body1)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.label_height),
                            style = MaterialTheme.typography.h6
                        )
                        Text(
                            text = "${peopleDetails.height}cm",
                            style = MaterialTheme.typography.body1
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.label_home_world),
                            style = MaterialTheme.typography.h6
                        )
                        Text(text = peopleDetails.homeworld, style = MaterialTheme.typography.body1)
                    }

                    if (peopleDetails.languages.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = stringResource(R.string.label_language),
                            style = MaterialTheme.typography.h5
                        )
                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            items(peopleDetails.languages.size) { i ->
                                val item = peopleDetails.languages[i]
                                if (i > 0) {
                                    Spacer(modifier = Modifier.height(5.dp))
                                }

                                Text(
                                    text = item,
                                    style = MaterialTheme.typography.body1
                                )

                                if (i < peopleDetails.films.size - 1) {
                                    Divider()
                                }
                            }
                        }
                    }

                    if (peopleDetails.films.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = stringResource(R.string.label_films),
                            style = MaterialTheme.typography.h5
                        )
                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            items(peopleDetails.films.size) { i ->
                                val item = peopleDetails.films[i]
                                if (i > 0) {
                                    Spacer(modifier = Modifier.height(20.dp))
                                }

                                FilmListItem(film = item, modifier = Modifier.fillMaxWidth())

                                if (i < peopleDetails.films.size - 1) {
                                    Divider()
                                }
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
