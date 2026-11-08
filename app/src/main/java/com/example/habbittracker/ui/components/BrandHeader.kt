package com.example.habbittracker.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habbittracker.R
import com.example.habbittracker.ui.theme.HabbitTrackerTheme
@Composable
fun BrandHeader(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier,
        ) {

        Image(
            painter = painterResource(R.drawable.ic_coinhabit_mark),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Text(
            text = "CoinHabit",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp
        )

    }


}

@Preview(
    name = "Шапка CoinHabit",
    showBackground = true,
    backgroundColor = 0xFFF8F6EE,
    widthDp = 393
)
@Composable
private fun BrandHeaderPreview() {
    HabbitTrackerTheme(darkTheme = false) {
        BrandHeader(
            modifier = Modifier.padding(24.dp)
        )
    }
}