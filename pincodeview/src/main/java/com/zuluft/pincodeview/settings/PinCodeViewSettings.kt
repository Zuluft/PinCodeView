package com.zuluft.pincodeview.settings

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import com.zuluft.pincodeview.R

class PinCodeViewSettings
constructor(
    private val context: Context,
    attrs: AttributeSet?
) {

    val keyboardMarginPercent: Float

    val bubblesMarginPercent: Float

    val keySizePercent: Float

    val bubbleSizePercent: Float

    val keyTextSize: Float

    val emptyBubbleStrokeWidth: Float

    val pinCodeSize: Int

    val keyPressAnimDuration: Long

    val bubbleAnimDuration: Long

    @ColorInt
    val keyTextColor: Int

    @ColorInt
    val keyIdleStateBgColor: Int

    @ColorInt
    val keyPressedStateBgColor: Int

    @ColorInt
    val emptyBubbleColor: Int

    @ColorInt
    val filledBubbleColor: Int

    private fun getFloatValue(@DimenRes resId: Int, typedValue: TypedValue): Float {
        context.resources.getValue(resId, typedValue, false)
        return typedValue.float
    }

    @ColorInt
    private fun getColor(@ColorRes resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }

    init {

        val typedValue = TypedValue()
        val defaultKeyboardMarginPercent =
            getFloatValue(
                R.dimen.defaultKeyboardMarginPercent,
                typedValue
            )
        val defaultBubblesMarginPercent =
            getFloatValue(
                R.dimen.defaultBubblesMarginPercent,
                typedValue
            )
        val defaultKeySizePercent =
            getFloatValue(
                R.dimen.defaultKeySizePercent,
                typedValue
            )
        val defaultBubbleSizePercent =
            getFloatValue(
                R.dimen.defaultBubbleSizePercent,
                typedValue
            )
        val defaultKeyTextSize =
            context.resources.getDimensionPixelSize(R.dimen.defaultKeyTextSize)

        val defaultKeyTextColor = getColor(R.color.defaultKeyTextColor)

        val defaultKeyIdleStateBgColor =
            getColor(R.color.defaultKeyIdleStateBgColor)

        val defaultKeyPressedStateBgColor =
            getColor(R.color.defaultKeyPressedStateBgColor)

        val defaultEmptyBubbleColor =
            getColor(R.color.defaultEmptyBubbleColor)

        val defaultFilledBubbleColor =
            getColor(R.color.defaultFilledBubbleColor)

        val defaultEmptyBubbleStrokeWidth =
            context.resources.getDimensionPixelSize(R.dimen.defaultEmptyBubbleStrokeWidth)

        val defaultPinCodeSize = context.resources.getInteger(R.integer.defaultPinCodeSize)

        val defaultKeyPressAnimDuration =
            context.resources.getInteger(R.integer.defaultKeyPressAnimDuration)

        val defaultBubbleAnimDuration =
            context.resources.getInteger(R.integer.defaultBubbleAnimDuration)

        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.PinCodeView
        )
        keyboardMarginPercent = typedArray.getFloat(
            R.styleable.PinCodeView_keyboardMarginPercent,
            defaultKeyboardMarginPercent
        )

        bubblesMarginPercent = typedArray.getFloat(
            R.styleable.PinCodeView_bubblesMarginPercent,
            defaultBubblesMarginPercent
        )

        keySizePercent = typedArray.getFloat(
            R.styleable.PinCodeView_keySizePercent,
            defaultKeySizePercent
        )

        bubbleSizePercent = typedArray.getFloat(
            R.styleable.PinCodeView_bubbleSizePercent,
            defaultBubbleSizePercent
        )

        keyTextSize = typedArray.getDimensionPixelSize(
            R.styleable.PinCodeView_keyTextSize,
            defaultKeyTextSize
        ).toFloat()

        keyTextColor = typedArray.getColor(
            R.styleable.PinCodeView_keyTextColor,
            defaultKeyTextColor
        )

        keyIdleStateBgColor = typedArray.getColor(
            R.styleable.PinCodeView_keyIdleStateBgColor,
            defaultKeyIdleStateBgColor
        )

        keyPressedStateBgColor = typedArray.getColor(
            R.styleable.PinCodeView_keyPressedStateBgColor,
            defaultKeyPressedStateBgColor
        )

        emptyBubbleColor = typedArray.getColor(
            R.styleable.PinCodeView_emptyBubbleColor,
            defaultEmptyBubbleColor
        )

        filledBubbleColor = typedArray.getColor(
            R.styleable.PinCodeView_filledBubbleColor,
            defaultFilledBubbleColor
        )
        emptyBubbleStrokeWidth = typedArray.getDimensionPixelSize(
            R.styleable.PinCodeView_emptyBubbleStrokeWidth,
            defaultEmptyBubbleStrokeWidth
        ).toFloat()

        pinCodeSize = typedArray.getInteger(
            R.styleable.PinCodeView_pinCodeSize,
            defaultPinCodeSize
        )
        keyPressAnimDuration = typedArray.getInteger(
            R.styleable.PinCodeView_keyPressAnimDuration,
            defaultKeyPressAnimDuration
        ).toLong()
        bubbleAnimDuration = typedArray.getInteger(
            R.styleable.PinCodeView_bubbleAnimDuration,
            defaultBubbleAnimDuration
        ).toLong()
        typedArray.recycle()
    }
}