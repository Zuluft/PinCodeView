package com.zuluft.pincodeview.shapes

import com.zuluft.pincodeview.shapes.base.ComponentShape

class CircularShape
constructor(
    private val cx: Float,
    private val cy: Float,
    private val radius: Float
) :
    ComponentShape {
    override fun getX(): Float {
        return cx
    }

    override fun getY(): Float {
        return cy
    }

    fun getRadius(): Float {
        return radius
    }

    override fun contains(x: Float, y: Float): Boolean {
        val dx = x - cx
        val dy = y - cy
        return dx * dx + dy * dy < radius * radius
    }
}