package com.zuluft.pincodeview.shapes.base

interface ComponentShape {

    fun getX(): Float
    fun getY(): Float

    fun contains(x: Float, y: Float): Boolean
}