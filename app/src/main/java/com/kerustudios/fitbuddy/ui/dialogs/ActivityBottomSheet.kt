package com.kerustudios.fitbuddy.ui.dialogs

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kerustudios.fitbuddy.data.entities.ActivityModel
import com.kerustudios.fitbuddy.data.entities.commonWorkouts
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityBottomSheet(
    onSave: (ActivityModel) -> Unit = {},
    onDissmiss: () -> Unit = {}
) {
    val context = LocalContext.current
    var activityModel by remember {
        mutableStateOf(
            ActivityModel(
                id = UUID.randomUUID().hashCode(),
                icon = "",
                reps = 0,
                name = "",
                units = ""
            )
        )
    }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = { onDissmiss() },
        modifier = Modifier.imePadding(),
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier.imePadding()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Enter workout detail  s below",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.alpha(.75f)
                )
                TextField(
                    value = activityModel.name ?: "",
                    onValueChange = {
                        activityModel = activityModel.copy(
                            name = it
                        )
                    },
                    label = { Text("Enter a tag") },
                    modifier = Modifier.fillMaxWidth()
                )
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Text(text = "Or select: ")
                    }
                    items(commonWorkouts) {
                        Card {
                            Text(
                                text = "#" + it.name,
                                modifier = Modifier
                                    .padding(vertical = 4.dp, horizontal = 8.dp)
                                    .clickable {
                                        activityModel = activityModel.copy(
                                            name = it.name,
                                            icon = it.icon ?: ""
                                        )
                                    }
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Select an icon: ")
                    Card(onClick = { /*TODO*/ }, shape = CircleShape) {
                        Text(
                            text = activityModel.icon.ifBlank { "🚶‍♂️" },
                            modifier = Modifier.padding(16.dp),
                            fontSize = 28.sp
                        )
                    }
                }
                TextField(
                    value = activityModel.reps.toString(),
                    onValueChange = {
                        activityModel = activityModel.copy(
                            reps = if (it.isNotBlank()) it.toInt() else 0
                        )
                    },
                    label = { Text("Enter number of reps/duration") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                TextField(
                    value = activityModel.units,
                    onValueChange = {
                        activityModel = activityModel.copy(
                            units = it
                        )
                    },
                    label = {
                        Text("Enter units e.g hours, sets, etc.")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(onClick = {
                    if (activityModel.name?.isNotBlank() == true && activityModel.reps > 0) {
                        onSave(activityModel)
                    } else {
                        Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT)
                            .show()
                    }
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Save")
                }
            }
        }
    }
}

@Preview
@Composable
private fun ActivityBottomSheetPreview() {
    ActivityBottomSheet()
}