package com.mr0xf00.lazytreelist.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mr0xf00.lazytreelist.*
import com.mr0xf00.lazytreelist.data.DataContainer
import com.mr0xf00.lazytreelist.data.DataItem
import com.mr0xf00.lazytreelist.data.DataNode
import com.mr0xf00.lazytreelist.data.testData

@Composable
fun App() {
    val data = remember { testData() }
    val expansionState = remember { ExpansionState() }
    Column {
        Surface(color = MaterialTheme.colors.primary, modifier = Modifier.fillMaxWidth()) {
            ExpansionIndicator(
                state = expansionState,
            ) { _, _, key ->
                Text(key as String)
            }
        }
        LazyTreeList(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            expansionState = expansionState,
            items = data.toItemTree(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        )
    }
}

private fun List<DataNode>.toItemTree(): ItemTree = object : ItemTree {
    override val size: Int get() = this@toItemTree.size

    override fun key(index: Int): Any = this@toItemTree[index].name

    @Composable
    override fun LazyItemScope.Item(
        index: Int,
        depth: Int,
        placement: ItemPlacement,
        expanded: Boolean,
        onExpand: () -> Unit
    ) {
        val data = this@toItemTree[index]
        DepthStyle(
            depth = depth, modifier = Modifier
                .fillMaxWidth()
        ) {
            val modifier = Modifier
                .fillMaxWidth()
            when (data) {
                is DataContainer -> ContainerItem(
                    name = data.name,
                    isExpanded = expanded,
                    onExpand = onExpand,
                    modifier = modifier
                )
                is DataItem -> LeafItem(name = data.name, modifier = modifier)
            }
        }
    }

    override fun expand(index: Int): ItemTree? {
        return when (val data = this@toItemTree[index]) {
            is DataContainer -> data.children.toItemTree()
            is DataItem -> null
        }
    }
}

