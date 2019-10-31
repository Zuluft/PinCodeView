package com.zuluft.pincodeview.valuesHolders

import androidx.annotation.ColorInt

data class KeyPressAnimatorValuesHolder(
    @ColorInt
    val keyComponentIdleColor: Int,
    @ColorInt
    val keyComponentPressedColor: Int,
    val animDuration: Long
)