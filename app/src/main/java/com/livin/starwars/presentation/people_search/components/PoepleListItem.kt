package com.livin.starwars.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.livin.starwars.domain.entity.People

@Composable
fun PeopleListItem(people: People, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = people.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}