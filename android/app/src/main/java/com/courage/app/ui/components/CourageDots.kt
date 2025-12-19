package com.courage.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface

@Composable
fun CourageDots(
    current: Int,
    max: Int = 5,
    dotSizeDp: Int = 10,
    spacingDp: Int = 6,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(spacingDp.dp)) {
        for (i in 1..max) {
            val filled = i <= current
            Surface(
                modifier = Modifier
                    .size(dotSizeDp.dp)
                    .alpha(if (filled) 1f else 0.25f),
                shape = CircleShape,
                color = if (filled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
            ) {}
        }
    }
}


