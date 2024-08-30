package com.kerustudios.fitbuddy.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HabitCard(modifier: Modifier = Modifier) {
    ElevatedCard(onClick = { /*TODO*/ }, modifier = modifier) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "💧")
                    Text(
                        text = "Water",
                        modifier = Modifier.alpha(.75f),
                        fontSize = 12.sp
                    )
                }
                Text(text = "5.4 liters", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "",
                modifier = Modifier
                    .alpha(.75f)
                    .size(18.dp)
            )
        }
    }
}

@Preview
@Composable
private fun HabitCardPreview() {
    HabitCard()
}