package com.mr0xf00.lazytreelist

public inline class ItemPlacement private constructor(private val value: Int) {
    public constructor(isFirst: Boolean, isMiddle: Boolean, isLast: Boolean) : this(
        (if (isFirst) 0x1 else 0) or ((if (isMiddle) 0x2 else 0)) or ((if (isLast) 0x4 else 0))
    )

    public val isFirst get() = value and 0x1 != 0
    public val isMiddle get() = value and 0x2 != 0
    public val isLast get() = value and 0x4 != 0
}

public fun ItemPlacement(pos: Int, size: Int): ItemPlacement {
    val isFirst = pos == 0
    val isLast = pos == size - 1
    return ItemPlacement(isFirst = isFirst, isMiddle = !isFirst && !isLast, isLast = isLast)
}