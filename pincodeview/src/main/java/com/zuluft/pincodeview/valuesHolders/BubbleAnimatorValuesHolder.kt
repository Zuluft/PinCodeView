package com.zuluft.pincodeview.valuesHolders

import androidx.annotation.ColorInt

data class BubbleAnimatorValuesHolder(
    @ColorInt
    val emptyBubbleColor: Int,
    @ColorInt
    val filledBubbleColor: Int,
    val animDuration: Long
)