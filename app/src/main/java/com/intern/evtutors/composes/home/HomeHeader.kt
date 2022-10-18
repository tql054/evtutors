package com.intern.evtutors.composes.home

import android.widget.ImageButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier
) {
    var searchKey by remember{ mutableStateOf("")}

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 5.dp)
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Category button",
            )
        }


        OutlinedTextField(
            value = searchKey,
            onValueChange = {},
            label = { Text(text = "Search for tutors")},
            singleLine = true,
            modifier = Modifier.weight(1f).padding(end = 20.dp),
            textStyle = TextStyle(fontSize = 18.sp,fontWeight = FontWeight.Bold),
            trailingIcon = {
                IconButton(onClick = {/*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Search,
                        contentDescription ="Icon Search" )

                }
            }
        )
    }
}

