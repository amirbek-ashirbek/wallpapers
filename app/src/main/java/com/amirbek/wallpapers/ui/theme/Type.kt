package com.amirbek.wallpapers.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.amirbek.wallpapers.R

// Set of Material typography styles to start with
val GilroyFontFamily = FontFamily(
	Font(R.font.gilroy_thin, FontWeight.Thin),
	Font(R.font.gilroy_extra_light, FontWeight.ExtraLight),
	Font(R.font.gilroy_light, FontWeight.Light),
	Font(R.font.gilroy_regular, FontWeight.Normal),
	Font(R.font.gilroy_medium, FontWeight.Medium),
	Font(R.font.gilroy_semi_bold, FontWeight.SemiBold),
	Font(R.font.gilroy_bold, FontWeight.Bold),
	Font(R.font.gilroy_extra_bold, FontWeight.ExtraBold),
	Font(R.font.gilroy_black, FontWeight.Black)
)

val Typography = Typography(
	bodyLarge = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.5.sp
	)
	/* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
).defaultFontFamily(fontFamily = GilroyFontFamily)

fun Typography.defaultFontFamily(fontFamily: FontFamily): Typography {
	return this.copy(
		displayLarge = this.displayLarge.copy(fontFamily = fontFamily),
		displayMedium = this.displayMedium.copy(fontFamily = fontFamily),
		displaySmall = this.displaySmall.copy(fontFamily = fontFamily),
		headlineLarge = this.headlineLarge.copy(fontFamily = fontFamily),
		headlineMedium = this.headlineMedium.copy(fontFamily = fontFamily),
		headlineSmall = this.headlineSmall.copy(fontFamily = fontFamily),
		titleLarge = this.titleLarge.copy(fontFamily = fontFamily),
		titleMedium = this.titleMedium.copy(fontFamily = fontFamily),
		titleSmall = this.titleSmall.copy(fontFamily = fontFamily),
		bodyLarge = this.bodyLarge.copy(fontFamily = fontFamily),
		bodyMedium = this.bodyMedium.copy(fontFamily = fontFamily),
		bodySmall = this.bodySmall.copy(fontFamily = fontFamily),
		labelLarge = this.labelLarge.copy(fontFamily = fontFamily),
		labelMedium = this.labelMedium.copy(fontFamily = fontFamily),
		labelSmall = this.labelSmall.copy(fontFamily = fontFamily)
	)
}