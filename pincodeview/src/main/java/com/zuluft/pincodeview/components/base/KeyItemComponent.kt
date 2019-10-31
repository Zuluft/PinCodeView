package com.zuluft.pincodeview.components.base

import android.graphics.Paint
import com.zuluft.pincodeview.shapes.base.ComponentShape

interface KeyItemComponent<SHAPE : ComponentShape> :
    ItemComponent<SHAPE> {
    fun getNewPinCode(currentPin: String): String
    fun getTextPaint(): Paint
}