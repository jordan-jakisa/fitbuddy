package com.kerustudios.fitbuddy.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kerustudios.fitbuddy.data.entities.ActivityModel


@Composable
fun ActivityCard(activityModel: ActivityModel) {
    ElevatedCard {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = activityModel.icon, fontSize = 32.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(text = activityModel.reps.toString(), fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = activityModel.units,
                    fontSize = 10.sp,
                    modifier = Modifier.alpha(.75f)
                )
            }
            Text(
                text = activityModel.name ?: "...",
                modifier = Modifier.alpha(.75f),
                fontSize = 12.sp
            )
        }
    }
}