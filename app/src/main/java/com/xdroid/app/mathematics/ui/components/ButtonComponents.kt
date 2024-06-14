package com.xdroid.app.mathematics.ui.components

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xdroid.app.mathematics.ui.theme.black
import com.xdroid.app.mathematics.ui.theme.colorPrimary
import com.xdroid.app.mathematics.ui.theme.regular
import com.xdroid.app.mathematics.ui.theme.white


@Composable
fun ButtonCompo(
    isPrimary: Boolean = true,
    label: String,
    onClick: () -> Unit,
    width: Int = responsiveWidth(baseWidth = 150)
) {

    if (isPrimary) {
        ButtonComponent(label = label, onClick = onClick, width = width)
    } else {
        OutlineButtonComponent(label = label, onClick = onClick, width = width)
    }


}

@Composable
fun OutlineButtonComponent(
    label: String,
    onClick: () -> Unit,
    width: Int = responsiveWidth(baseWidth = 150)
) {
    OutlinedButton(
        modifier = Modifier
            .width(width.dp)
            .padding(horizontal = 8.dp),
        onClick = { onClick() },
        border = BorderStroke(1.dp, white), // Border color and width
        content = {
            Text(label, color = white, style = regular)
        },


        shape = RoundedCornerShape(5.dp),
    )
}

@Composable
fun ButtonComponent(
    label: String,
    onClick: () -> Unit,
    width: Int = responsiveWidth(baseWidth = 150)
) {

    Button(
        modifier = Modifier
            .width(width.dp)
            .padding(horizontal = 4.dp),
        onClick = { onClick() },
        shape = RoundedCornerShape(5.dp),

        ) {
        Text(label, style = regular)
    }
}

@Composable
fun ButtonComponentSmall(
    label: String,
    onClick: () -> Unit,
    width: Int = responsiveWidth(baseWidth = 150)
) {

    Button(
        modifier = Modifier
            .width(width.dp)
            .height(30.dp)
            .padding(horizontal = 4.dp),
        onClick = { onClick() },
        shape = RoundedCornerShape(5.dp),

        ) {
        Text(label, style = regular)
    }
}


@Composable
fun IconButtons(
    icon: ImageVector,
    modifier: Modifier,
    label: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        shape = RoundedCornerShape(5.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                icon,
                contentDescription = label,
                modifier = Modifier.padding(end = 8.dp),
                tint = white
            )
            Text(
                label, style = regular,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }

}

@Composable
fun IconOutlineButton(
    icon: ImageVector,
    modifier: Modifier,
    label: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = { onClick() },
        border = BorderStroke(1.dp, black),
        shape = RoundedCornerShape(5.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                icon,
                contentDescription = label,
                modifier = Modifier.padding(end = 8.dp),
                tint = colorPrimary
            )
            Text(
                label,
                color = black,
                style = regular,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }

}

@Composable
fun IconOutlineButton2(
    icon: Int,
    modifier: Modifier,
    label: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = { onClick() },
        border = BorderStroke(1.dp, black),
        shape = RoundedCornerShape(5.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            val imagePainter: Painter = painterResource(id = icon)
            Image(
                painter = imagePainter,
                contentDescription = null, // Provide a content description if needed
                modifier = Modifier.size(24.dp) // Add padding as needed
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                label,
                color = black,
                style = regular,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }

}

@Composable
fun responsiveWidth(baseWidth: Dp): Dp {
    val configuration = LocalConfiguration.current
    val fontScale = configuration.fontScale
    // Adjust the multiplier or add logic for min/max width as needed
    return remember(fontScale) { baseWidth * fontScale }
}

@Composable
fun responsiveWidth(baseWidth: Int): Int {
    val configuration = LocalConfiguration.current
    val fontScale = configuration.fontScale
    // Adjust the multiplier or add logic for min/max width as needed
    return remember(fontScale) { baseWidth * fontScale }.toInt()
}

@Composable
fun getScreenWidth(): Float {
    // Access the current configuration to get the screen width
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp

    // Calculate half of the screen width
    // Note: screenWidthDp.value gives you the float value of dp
    return screenWidthDp.value - 32
}

@Composable
fun getScreenWidthResponsive(): Float {
    // Access the current configuration to get the screen width
    val configuration = LocalConfiguration.current
    var screenWidthDp = configuration.screenWidthDp.dp
    val screenOrientation = configuration.orientation

    if (screenOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
        screenWidthDp = configuration.screenHeightDp.dp
    }

    // Calculate half of the screen width
    // Note: screenWidthDp.value gives you the float value of dp
    return screenWidthDp.value - 38
}

@Composable
fun getWidthAlertImage(): Dp {
    // Access the current configuration to get the screen width
    val configuration = LocalConfiguration.current
    var screenWidthDp = 150f
    val screenOrientation = configuration.orientation

    if (screenOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
        screenWidthDp = 50f
    }

    return screenWidthDp.dp
}

@Composable
fun getHalfScreenWidth(): Float {
    // Access the current configuration to get the screen width
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp

    // Calculate half of the screen width
    // Note: screenWidthDp.value gives you the float value of dp
    return screenWidthDp.value / 2 - 38
}


fun isPortrait(configurationInfo: Configuration): Boolean {
    // Access the current configuration to get the screen orientation
    val screenOrientation = configurationInfo.orientation

    // Return true if the screen orientation is portrait
    return screenOrientation == Configuration.ORIENTATION_PORTRAIT
}