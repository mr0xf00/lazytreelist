package com.mr0xf00.lazytreelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
inline fun ExpansionIndicator(
    modifier: Modifier = Modifier,
    state: ExpansionState,
    crossinline content: @Composable BoxScope.(depth: Int, index: Int, key: Any) -> Unit,
) {
    val listState = rememberLazyListState()

    LaunchedEffect(state.size) {
        delay(100)
        state.size.let { size ->
            if (size > 0) listState.animateScrollToItem(size - 1)
        }
    }

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        state = listState,
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth()
    ) {
        item {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
        items(count = state.size) { depth ->
            if (depth > 0) Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp)
                    .clickable {
                        state.size = depth + 1
                    },
                contentAlignment = Alignment.Center
            ) {
                val entry = state[depth]
                content(depth = depth, index = entry.index, key = entry.key)
            }
        }
    }
}
