package com.mr0xf00.lazytreelist.data

import kotlin.random.Random

sealed interface DataNode {
    val name: String
}

data class DataItem(override val name: String) : DataNode
data class DataContainer(override val name: String, val children: List<DataNode>) : DataNode

fun testItem(index: Int) = DataItem("Item $index")
fun testContainer(depth: Int, index: Int): DataContainer = DataContainer(
    "Container $index",
    children = testNodes(depth)
)

private fun testNodes(depth: Int): List<DataNode> {
    return List(size = (5..10).random()) { i ->
        if (depth <= 5 && Random.nextBoolean()) testContainer(depth + 1, i)
        else testItem(i)
    }
}

fun testData() : List<DataNode> = testNodes(0)