package com.kerustudios.fitbuddy.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HabitDialog(
    modifier: Modifier = Modifier,
    habit: Habit = Habit.WATER,
    onSave: (Habit, Int) -> Unit

) {
    var value by rememberSaveable {
        mutableIntStateOf(0)
    }

    var units by rememberSaveable {
        mutableStateOf(if (habit == Habit.WATER) "liters" else "hours")
    }

    ElevatedCard(onClick = { /*TODO*/ }) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Enter details below",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.alpha(.75f)
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = value.toString(),
                    onValueChange = { value = it.toInt() },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
                Spacer(modifier = Modifier.width(16.dp))
                TextField(
                    value = units,
                    onValueChange = { units = it },
                    modifier = Modifier.weight(1f),
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.ArrowDropDown,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                )
            }
            Button(onClick = {
                onSave(habit, value)
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Save")
            }
        }
    }
}

enum class Habit {
    WATER,
    SLEEP
}

@Preview
@Composable
private fun HabitDialogPreview() {
    HabitDialog(
        onSave = { _, _ -> }
    )
}