package com.zuluft.pincodeview.animators

import android.animation.ValueAnimator
import android.graphics.Paint
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.zuluft.pincodeview.components.BubbleComponent
import com.zuluft.pincodeview.valuesHolders.BubbleAnimatorValuesHolder
import java.lang.ref.WeakReference

class BubbleAnimator(
    view: View,
    private val bubbleAnimatorValuesHolder: BubbleAnimatorValuesHolder
) {

    private val viewWeakRef: WeakReference<View> = WeakReference(view)
    private var currentAnimator: ValueAnimator? = null

    fun animateBubble(bubbleComponent: BubbleComponent) {
        if (currentAnimator != null && currentAnimator!!.isRunning) {
            currentAnimator!!.pause()
            currentAnimator!!.removeAllListeners()
            currentAnimator!!.cancel()
        }
        bubbleComponent.getPaint().style = Paint.Style.FILL_AND_STROKE
        currentAnimator = ValueAnimator.ofArgb(
            bubbleAnimatorValuesHolder.emptyBubbleColor,
            bubbleAnimatorValuesHolder.filledBubbleColor
        )
            .apply {
                duration = bubbleAnimatorValuesHolder.animDuration
                interpolator = DecelerateInterpolator()
                repeatMode = ValueAnimator.REVERSE
                repeatCount = 1
                addUpdateListener {
                    bubbleComponent.getPaint().color = it.animatedValue as Int
                    viewWeakRef.get()?.invalidate()
                }
            }.also {
                it.start()
            }
    }
}