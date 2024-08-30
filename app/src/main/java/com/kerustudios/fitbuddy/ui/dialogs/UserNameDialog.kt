package com.kerustudios.fitbuddy.ui.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog

@Composable
fun UserNameDialog(modifier: Modifier = Modifier) {
    var name by rememberSaveable {
        mutableStateOf("")
    }
    Dialog(onDismissRequest = { /*TODO*/ }) {
        ElevatedCard(onClick = { /*TODO*/ }) {
            Column(modifier = modifier) {
                Text(text = "Hello there \uD83D\uDC4B")
                Text(text = "May i know your name please")
                TextField(value = name, onValueChange = {
                    name = it
                }, modifier = Modifier.fillMaxWidth(), trailingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Rounded.Done, contentDescription ="")

                    }
                })

            }

        }
    }
}

@Preview
@Composable
private fun UserNameDialogPreview() {
    UserNameDialog()

}
