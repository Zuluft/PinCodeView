package com.zuluft.pincodeview.components

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import com.zuluft.pincodeview.components.base.KeyItemComponent
import com.zuluft.pincodeview.shapes.CircularShape
import com.zuluft.pincodeview.valuesHolders.NumberKeyComponentValuesHolder

class NumberKeyComponent
constructor(
    private val key: Int,
    private val circularShape: CircularShape,
    private val numberKeyComponentValuesHolder: NumberKeyComponentValuesHolder
) : KeyItemComponent<CircularShape> {

    private val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        .apply {
            style = Paint.Style.FILL
            color = numberKeyComponentValuesHolder.keyIdleStateBgColor
        }

    private val txtPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        .apply {
            style = Paint.Style.FILL_AND_STROKE
            color = numberKeyComponentValuesHolder.keyTextColor
            textSize = numberKeyComponentValuesHolder.textSize
            textAlign = Paint.Align.CENTER
        }

    private val textBounds = Rect()


    override fun getShape(): CircularShape {
        return circularShape
    }

    override fun getNewPinCode(currentPin: String): String {
        return currentPin + key
    }


    override fun draw(canvas: Canvas) {
        canvas.drawCircle(
            circularShape.getX(),
            circularShape.getY(),
            circularShape.getRadius(),
            bgPaint
        )
        txtPaint.getTextBounds(key.toString(), 0, 1, textBounds)
        canvas.drawText(
            key.toString(),
            circularShape.getX(),
            circularShape.getY() + textBounds.height() / 2f,
            txtPaint
        )
    }

    override fun getTextPaint(): Paint {
        return txtPaint
    }

    override fun getPaint(): Paint {
        return bgPaint
    }
}
