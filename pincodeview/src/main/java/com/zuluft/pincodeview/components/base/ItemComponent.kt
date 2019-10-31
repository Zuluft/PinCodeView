package com.zuluft.pincodeview.components.base

import android.graphics.Canvas
import android.graphics.Paint
import com.zuluft.pincodeview.shapes.base.ComponentShape

interface ItemComponent<SHAPE : ComponentShape> {
    fun draw(canvas: Canvas)
    fun getShape(): SHAPE
    fun getPaint(): Paint
}