package ru.take.it.word.learn.game.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.take.it.word.learn.game.R

private object AppFont {
    val Default = FontFamily(
        Font(R.font.regular, FontWeight.Normal),
        Font(R.font.bold, FontWeight.Bold),
        Font(R.font.extra_bold, FontWeight.ExtraBold)
    )
}

val ExtraBold = TextStyle(
    fontFamily = AppFont.Default,
    fontWeight = FontWeight.ExtraBold
)

val Bold = TextStyle(
    fontFamily = AppFont.Default,
    fontWeight = FontWeight.Bold
)

val Regular = TextStyle(
    fontFamily = AppFont.Default,
    fontWeight = FontWeight.Normal,
)

val Regular_10 = Regular.copy(
    fontSize = 10.sp,
)

val Regular_12 = Regular.copy(
    fontSize = 12.sp,
)

val Regular_13 = Regular.copy(
    fontSize = 13.sp,
)

val Regular_14 = Regular.copy(
    fontSize = 14.sp,
)

val Regular_16 = Regular.copy(
    fontSize = 16.sp,
)

val Bold_16 = Bold.copy(
    fontSize = 16.sp,
)

val Bold_20 = Bold.copy(
    fontSize = 20.sp
)

val ExtraBold_15 = ExtraBold.copy(
    fontSize = 15.sp,
)

val ExtraBold_16 = ExtraBold.copy(
    fontSize = 16.sp,
)