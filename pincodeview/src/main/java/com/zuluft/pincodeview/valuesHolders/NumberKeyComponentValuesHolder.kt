package com.zuluft.pincodeview.valuesHolders

import androidx.annotation.ColorInt

data class NumberKeyComponentValuesHolder(
    val keyTextColor: Int,
    @ColorInt
    val keyIdleStateBgColor: Int,
    @ColorInt
    val keyPressedStateBgColor: Int,
    val textSize: Float
)