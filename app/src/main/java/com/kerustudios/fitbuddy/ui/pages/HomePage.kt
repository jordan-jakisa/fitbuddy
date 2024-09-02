package com.kerustudios.fitbuddy.ui.pages


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kerustudios.fitbuddy.data.entities.ActivityModel
import com.kerustudios.fitbuddy.ui.components.HabitCard
import com.kerustudios.fitbuddy.ui.components.ProgressGraph
import com.kerustudios.fitbuddy.ui.dialogs.Habit
import com.kerustudios.fitbuddy.ui.dialogs.HabitDialog
import com.kerustudios.fitbuddy.ui.dialogs.UserNameDialog
import com.kerustudios.fitbuddy.ui.viewmodels.AppEvents
import com.kerustudios.fitbuddy.ui.viewmodels.HomeUiState
import com.kerustudios.fitbuddy.ui.viewmodels.HomeViewModel
import com.kerustudios.fitbuddy.utils.getToday

@Composable
fun Home(
    modifier: Modifier = Modifier, vm: HomeViewModel = hiltViewModel()
) {
    val uiState by vm.uiState.collectAsState(initial = HomeUiState())
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = modifier
            .drawBehind {
                drawRect(
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            0f to Color(0x332037FF),
                            .56f to Color(0x1A6E6E6E),
                            1f to Color(0x4D2037FF),
                        )
                    )
                )
            }
            .padding(16.dp)
            .verticalScroll(scrollState)
            .padding(bottom = 154.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                text = "😁 Good day, " + (uiState.userName ?: "mate") + "!",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Track your daily activity and stay healthy",
                fontSize = 14.sp,
                modifier = Modifier.alpha(.75f)
            )
            TodayCard(modifier = Modifier.fillMaxWidth(.25f))
            Text(text = "Today activity", modifier = Modifier.alpha(.75f), fontSize = 14.sp)
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(uiState.activities) {
                    ActivityCard(it)
                }
            }

            Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                HabitCard(modifier = Modifier.weight(1f),
                    title = "Water",
                    emoji = "💧",
                    value = (uiState.totalWater ?: 0f).toString() + " liters",
                    onClick = {
                        vm.showAddWaterDialog()
                    })
                HabitCard(modifier = Modifier.weight(1f),
                    title = "Sleep",
                    emoji = "😴",
                    value = (uiState.totalSleep ?: 0f).toString() + " hours",
                    onClick = {
                        vm.showAddSleepDialog()

                    })
            }

            ProgressGraph(
                modifier = Modifier
                    .height(300.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text(text = "Log workout")
        }
    }

    uiState.appEvents?.let {
        if (it.isNotEmpty()) {
            when (it.first()) {
                is AppEvents.IsFirstTimeUser -> {
                    UserNameDialog { name ->
                        vm.updateUserName(name)
                    }
                }

                is AppEvents.ShowAddSleepDialog -> {
                    HabitDialog(habit = Habit.SLEEP, onSave = { habit, value ->
                        vm.saveEntry(value, habit)
                    })
                }

                is AppEvents.ShowAddWaterDialog -> {
                    HabitDialog(habit = Habit.WATER, onSave = { habit, value ->
                        vm.saveEntry(value, habit)
                    })
                }

                else -> {
                    // Do nothing
                }
            }
        }
    }
}

@Composable
fun TodayCard(modifier: Modifier = Modifier) {
    val (dayOfMonth, dayOfWeek, month) = getToday()

    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = dayOfWeek,
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = Color.Black)
                    .padding(2.dp),
                color = Color.White,
                textAlign = TextAlign.Center,

                )
            Text(
                dayOfMonth,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(
                month,
                modifier = modifier
                    .fillMaxWidth()
                    .alpha(.75f)
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun ActivityCard(activityModel: ActivityModel) {
    ElevatedCard {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = activityModel.icon, fontSize = 32.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = activityModel.reps.toString(), fontWeight = FontWeight.Bold)
            Text(
                text = activityModel.name ?: "...",
                modifier = Modifier.alpha(.75f),
                fontSize = 12.sp
            )
        }
    }
}