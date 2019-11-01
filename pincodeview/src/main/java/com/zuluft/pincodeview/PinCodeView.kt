package com.zuluft.pincodeview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.zuluft.pincodeview.animators.BubbleAnimator
import com.zuluft.pincodeview.animators.KeyPressAnimator
import com.zuluft.pincodeview.components.BubbleComponent
import com.zuluft.pincodeview.components.NumberKeyComponent
import com.zuluft.pincodeview.components.base.KeyItemComponent
import com.zuluft.pincodeview.settings.PinCodeViewSettings
import com.zuluft.pincodeview.shapes.CircularShape
import com.zuluft.pincodeview.valuesHolders.BubbleAnimatorValuesHolder
import com.zuluft.pincodeview.valuesHolders.BubbleComponentValuesHolder
import com.zuluft.pincodeview.valuesHolders.KeyPressAnimatorValuesHolder
import com.zuluft.pincodeview.valuesHolders.NumberKeyComponentValuesHolder
import java.util.function.Predicate

private const val KEYS_PER_ROW_COUNT = 3

private val NUMBER_KEYS = arrayOf(
    7, 8, 9,
    4, 5, 6,
    1, 2, 3
)

class PinCodeView :
    View {

    private val pinCodeViewSettings: PinCodeViewSettings
    private val numberKeyComponentValuesHolder: NumberKeyComponentValuesHolder
    private val bubbleComponentValuesHolder: BubbleComponentValuesHolder
    private val keyPressAnimator: KeyPressAnimator
    private val bubbleAnimator: BubbleAnimator

    private var onPinCodeEnterFinishedListener: ((String) -> Unit)? = null

    private val keyItemComponents = ArrayList<KeyItemComponent<CircularShape>>()
    private val bubbleItemComponents = ArrayList<BubbleComponent>()

    private var currentPin: String = ""

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        pinCodeViewSettings = PinCodeViewSettings(context, attrs)
        numberKeyComponentValuesHolder = NumberKeyComponentValuesHolder(
            pinCodeViewSettings.keyTextColor,
            pinCodeViewSettings.keyIdleStateBgColor,
            pinCodeViewSettings.keyPressedStateBgColor,
            pinCodeViewSettings.keyTextSize
        )
        bubbleComponentValuesHolder = BubbleComponentValuesHolder(
            pinCodeViewSettings.bubblesMarginPercent,
            pinCodeViewSettings.bubbleSizePercent,
            pinCodeViewSettings.emptyBubbleColor,
            pinCodeViewSettings.filledBubbleColor,
            pinCodeViewSettings.emptyBubbleStrokeWidth
        )
        keyPressAnimator = KeyPressAnimator(
            this,
            KeyPressAnimatorValuesHolder(
                pinCodeViewSettings.keyIdleStateBgColor,
                pinCodeViewSettings.keyPressedStateBgColor,
                pinCodeViewSettings.keyPressAnimDuration
            )
        )
        bubbleAnimator = BubbleAnimator(
            this,
            BubbleAnimatorValuesHolder(
                pinCodeViewSettings.emptyBubbleColor,
                pinCodeViewSettings.filledBubbleColor,
                pinCodeViewSettings.bubbleAnimDuration
            )
        )
    }

    fun setOnPasscodeEnteredListener(predicate: (String) -> Unit) {
        this.onPinCodeEnterFinishedListener = predicate
    }

    private fun findKeyItemByCoordinates(x: Float, y: Float): KeyItemComponent<CircularShape>? {
        return keyItemComponents.findLast {
            it.getShape().contains(x, y)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            findKeyItemByCoordinates(event.x, event.y)?.also {
                currentPin = it.getNewPinCode(currentPin)
                keyPressAnimator.animateKeyPress(it)
                if (currentPin.length <= bubbleItemComponents.size) {
                    bubbleAnimator.animateBubble(bubbleItemComponents[currentPin.length - 1])
                }
                if (currentPin.length == pinCodeViewSettings.pinCodeSize &&
                    onPinCodeEnterFinishedListener != null
                ) {
                    onPinCodeEnterFinishedListener!!.invoke(currentPin)
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val keyboardMarginPixels = pinCodeViewSettings.keyboardMarginPercent * w
        val keySizePixels = pinCodeViewSettings.keySizePercent * w
        val gapBetweenKeyItems =
            (w - 2 * keyboardMarginPixels - KEYS_PER_ROW_COUNT * keySizePixels) /
                    (KEYS_PER_ROW_COUNT - 1)
        var startY = h - keyboardMarginPixels - keySizePixels
        var startX = (w - keySizePixels) / 2f
        val keyShapeRadius = keySizePixels / 2f
        keyItemComponents.clear()
        addComponent(
            0,
            CircularShape(startX + keyShapeRadius, startY + keyShapeRadius, keyShapeRadius)
        )
        NUMBER_KEYS.forEachIndexed { index, i ->
            if (index % KEYS_PER_ROW_COUNT == 0) {
                startX = keyboardMarginPixels
                startY -= gapBetweenKeyItems + keySizePixels
            }
            addComponent(
                i,
                CircularShape(
                    startX + keyShapeRadius,
                    startY + keyShapeRadius,
                    keyShapeRadius
                )
            )
            startX += keySizePixels + gapBetweenKeyItems
        }

        val bubblesMarginPixels = w * bubbleComponentValuesHolder.bubblesMarginPercent
        val bubbleSizePixels = w * bubbleComponentValuesHolder.bubbleSizePercent
        val gapBetweenBubbleItems =
            (w - 2 * bubblesMarginPixels - pinCodeViewSettings.pinCodeSize * bubbleSizePixels) / (pinCodeViewSettings.pinCodeSize - 1)
        val bubbleShapeRadius = bubbleSizePixels / 2f
        startY -= gapBetweenKeyItems + keySizePixels
        startX = bubblesMarginPixels
        for (i in 0 until pinCodeViewSettings.pinCodeSize) {
            bubbleItemComponents.add(
                BubbleComponent(
                    CircularShape(
                        startX + bubbleShapeRadius,
                        startY + bubbleShapeRadius, bubbleShapeRadius
                    ),
                    bubbleComponentValuesHolder
                )
            )
            startX += bubbleSizePixels + gapBetweenBubbleItems
        }


    }

    private fun addComponent(key: Int, circularShape: CircularShape) {
        keyItemComponents.add(
            NumberKeyComponent(
                key,
                circularShape,
                NumberKeyComponentValuesHolder(
                    pinCodeViewSettings.keyTextColor,
                    pinCodeViewSettings.keyIdleStateBgColor,
                    pinCodeViewSettings.keyPressedStateBgColor,
                    pinCodeViewSettings.keyTextSize
                )
            )
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        keyItemComponents.forEach {
            it.draw(canvas)
        }
        bubbleItemComponents.forEach {
            it.draw(canvas)
        }
    }


}