package com.zuluft.pincodeview.components

import android.graphics.Canvas
import android.graphics.Paint
import com.zuluft.pincodeview.components.base.ItemComponent
import com.zuluft.pincodeview.shapes.CircularShape
import com.zuluft.pincodeview.valuesHolders.BubbleComponentValuesHolder

class BubbleComponent
constructor(
    private val circularShape: CircularShape,
    private val bubbleComponentValuesHolder: BubbleComponentValuesHolder
) :
    ItemComponent<CircularShape> {
    private val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        .apply {
            style = Paint.Style.STROKE
            color = bubbleComponentValuesHolder.emptyBubbleColor
            strokeWidth = bubbleComponentValuesHolder.emptyBubbleStrokeWidth
        }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(
            circularShape.getX(),
            circularShape.getY(),
            circularShape.getRadius(),
            bgPaint
        )
    }

    override fun getPaint(): Paint {
        return bgPaint
    }

    override fun getShape(): CircularShape {
        return circularShape
    }
}