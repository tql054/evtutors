package com.miggue.mylogin01.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.intern.evtutors.R


private val Poppins = FontFamily(
    Font(R.font.poppins_regular),
    Font(R.font.poppins_light,weight = FontWeight.Light),
    Font(R.font.poppins_italic, style = FontStyle.Italic),
    Font(R.font.poppins_bold, weight = FontWeight.Bold),
    Font(R.font.poppins_black, weight = FontWeight.Black),
)

val quickSandFont = FontFamily(
    Font(R.font.quicksand_bold, FontWeight.Bold),
    Font(R.font.quicksand_light, FontWeight.Light),
    Font(R.font.quicksand_medium, FontWeight.Medium),
    Font(R.font.quicksand_regular, FontWeight.Normal),
    Font(R.font.quicksand_semibold, FontWeight.SemiBold)
)
val Typography = Typography(
    defaultFontFamily = Poppins,
    h1 = TextStyle(fontWeight = FontWeight.Bold, fontSize = 40.sp,color = Color.DarkGray)
)
val Typographyonboar = Typography(
    body1 = TextStyle(
        fontFamily = quickSandFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp
    )
)