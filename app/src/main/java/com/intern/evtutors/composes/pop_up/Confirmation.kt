package com.intern.evtutors.composes.pop_up

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.miggue.mylogin01.ui.theme.ModalColor

@Composable
fun Confirmation(
    title:String,
    cancelAction: ()->Unit,
    confirmAction: ()->Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ModalColor)
            .padding(
                top = 5.dp,
                start = 10.dp,
                end = 10.dp
            )
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .background(Color.White)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Confirm all change?")
            Row {

                Button(onClick = {

                }) {
                    Text(
                        text = "Yes"
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))

                Button(onClick = {

                }) {
                    Text(
                        text = "No"
                    )
                }

            }
        }
    }
}