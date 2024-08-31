package com.kerustudios.fitbuddy.ui.dialogs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun UserNameDialog(
    modifier: Modifier = Modifier,
    onSave: (String) -> Unit
) {
    var name by rememberSaveable {
        mutableStateOf("")
    }
    var error by rememberSaveable {
        mutableStateOf(false)
    }
    Dialog(onDismissRequest = { /*TODO*/ }) {
        ElevatedCard(onClick = { /*TODO*/ }) {
            Column(
                modifier = modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Hello there 👋",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "May i know your name please",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.alpha(.75f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = name,
                    onValueChange = {
                        name = it
                        error = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Enter your name here ... ")
                    }
                )
                AnimatedVisibility(
                    visible = error,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        text = "* name cannot be empty",
                        fontSize = 10.sp,
                        modifier = Modifier.alpha(.65f),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        if (name.isNotBlank()) onSave(name)
                        else error = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}

@Preview
@Composable
private fun UserNameDialogPreview() {
    UserNameDialog {}

}
