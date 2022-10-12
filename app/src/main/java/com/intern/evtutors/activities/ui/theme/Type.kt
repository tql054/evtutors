package com.intern.evtutors.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.intern.evtutors.R

// Set of Material typography styles to start with
private val Poppins = FontFamily(
    Font(R.font.poppins_regular),
    Font(R.font.poppins_light,weight = FontWeight.Light),
    Font(R.font.poppins_italic, style = FontStyle.Italic),
    Font(R.font.poppins_bold, weight = FontWeight.Bold),
    Font(R.font.poppins_black, weight = FontWeight.Black),
)
val Typography = Typography(
    defaultFontFamily = Poppins,
    h1 = TextStyle(fontWeight = FontWeight.Bold, fontSize = 40.sp,color = Color.DarkGray)
)
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
