package com.example.simpletodoapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.simpletodoapp.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val RobotoBlack = FontFamily(Font(R.font.roboto_black))
val RobotoBlackItalic = FontFamily(Font(R.font.roboto_blackitalic))
val RobotoBold = FontFamily(Font(R.font.roboto_bold))
val RobotoBoldItalic = FontFamily(Font(R.font.roboto_bolditalic))
val RobotoLight = FontFamily(Font(R.font.roboto_light))
val RobotoLightItalic = FontFamily(Font(R.font.roboto_lightitalic))
val RobotoMedium = FontFamily(Font(R.font.roboto_medium))
val RobotoMediumItalic = FontFamily(Font(R.font.roboto_mediumitalic))
val RobotoItalic = FontFamily(Font(R.font.roboto_italic))
val RobotoRegular = FontFamily(Font(R.font.roboto_regular))
val RobotoThin = FontFamily(Font(R.font.roboto_thin))
val RobotoThinItalic = FontFamily(Font(R.font.roboto_thinitalic))