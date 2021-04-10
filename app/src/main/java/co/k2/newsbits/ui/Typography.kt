package co.k2.newsbits.ui

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import co.k2.newsbits.R

val SailFont = FontFamily(
    Font(R.font.sail_regular)
)

val NewslyTypography = Typography(
    h3 = TextStyle(
        fontFamily = SailFont,
        fontWeight = FontWeight.W400,
        fontSize = 48.sp,
        shadow = Shadow(color = Color.LightGray)
    ),
    body1 = TextStyle(
        fontSize = 14.sp,
        letterSpacing = 0.3.sp,
        fontWeight = FontWeight.Medium,
    ),
    subtitle1 = TextStyle(
        fontSize = 12.sp
    ),
    caption = TextStyle(
        fontSize = 10.sp
    )
)
