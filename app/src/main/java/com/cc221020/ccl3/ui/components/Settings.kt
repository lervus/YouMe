package com.cc221020.ccl3.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun RotatingIconButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String? = null
) {
    var isPressed by remember { mutableStateOf(false) }

    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = Modifier
            .clickable {
                isPressed = !isPressed
                onClick()
            }
            .size(50.dp)
            .rotateIfPressed(isPressed),
        tint = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun Modifier.rotateIfPressed(isPressed: Boolean): Modifier {
    return if (isPressed) {
        this.graphicsLayer(rotationZ = 90f)
    } else {
        this
    }
}