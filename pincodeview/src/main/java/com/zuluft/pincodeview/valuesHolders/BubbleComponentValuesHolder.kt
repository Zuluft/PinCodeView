package com.zuluft.pincodeview.valuesHolders

import androidx.annotation.ColorInt

data class BubbleComponentValuesHolder(
    val bubblesMarginPercent: Float,
    val bubbleSizePercent: Float,
    @ColorInt
    val emptyBubbleColor: Int,
    @ColorInt
    val filledBubbleColor: Int,
    val emptyBubbleStrokeWidth: Float
)