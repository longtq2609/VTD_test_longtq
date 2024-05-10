package com.longtq.vtd_test_longtq.countries.list_countries_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.longtq.domain.entity.Countries

@Composable
fun CountriesCard(modifier: Modifier = Modifier, countries: Countries, onClickItem: () -> Unit) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable {
                onClickItem.invoke()
            },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
        ) {
            AsyncImage(
                model = countries.flags?.png,
                contentDescription = null,
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = countries.name?.common ?: "",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

        }

    }
}