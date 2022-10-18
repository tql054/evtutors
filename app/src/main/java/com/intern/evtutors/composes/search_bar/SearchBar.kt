package com.intern.evtutors.composes.search_bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.intern.evtutors.activities.SigInScreen
import com.miggue.mylogin01.ui.theme.FatherOfAppsTheme

@Composable
fun SearchBar(
    placeHolder:String,
    modifier: Modifier = Modifier
) {
    var searchKey by remember{ mutableStateOf("") }
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        color = Color(333)
    ) {
        TextField(value = searchKey, onValueChange = {})
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FatherOfAppsTheme {
        SearchBar("Search for")
    }
}