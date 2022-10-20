package com.mr0xf00.lazytreelist

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable

interface ItemTree {
    val size: Int
    fun key(index: Int): Any
    @Composable
    fun LazyItemScope.Item(
        index: Int,
        depth: Int,
        placement: ItemPlacement,
        expanded: Boolean,
        onExpand: () -> Unit
    )

    fun expand(index: Int): ItemTree?
}

internal object EmptyItemTree : ItemTree {
    override val size: Int get() = 0

    override fun key(index: Int): Any = error("EmptyItemTree does not contain any elements.")

    @Composable
    override fun LazyItemScope.Item(
        index: Int,
        depth: Int,
        placement: ItemPlacement,
        expanded: Boolean,
        onExpand: () -> Unit
    ) = Unit

    override fun expand(index: Int): ItemTree? = null
}