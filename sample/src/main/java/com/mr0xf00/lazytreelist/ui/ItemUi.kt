package com.mr0xf00.lazytreelist.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
inline fun DepthStyle(
    depth: Int,
    modifier: Modifier = Modifier,
    horPad: Dp = 16.dp,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .padding(start = horPad * depth)
    ) {
        content()
    }
}

@Composable
fun LeafItem(name: String, modifier: Modifier = Modifier) {
    Surface(
        elevation = 4.dp, modifier = modifier.height(50.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier.padding(8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(name, modifier = Modifier.padding(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContainerItem(
    name: String,
    isExpanded: Boolean,
    onExpand: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onExpand,
        elevation = if (isExpanded) 4.dp else 8.dp,
        modifier = modifier.height(50.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            Modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(name, modifier = Modifier.padding(8.dp))
            Icon(
                if (!isExpanded) Icons.Default.KeyboardArrowRight
                else Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }
    }
}
