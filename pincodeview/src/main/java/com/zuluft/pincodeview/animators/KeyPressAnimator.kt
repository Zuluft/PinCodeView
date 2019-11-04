package com.zuluft.pincodeview.animators

import android.animation.ValueAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.zuluft.pincodeview.components.base.KeyItemComponent
import com.zuluft.pincodeview.shapes.CircularShape
import com.zuluft.pincodeview.valuesHolders.KeyPressAnimatorValuesHolder
import java.lang.ref.WeakReference

class KeyPressAnimator(
    view: View,
    private val keyPressAnimatorValuesHolder: KeyPressAnimatorValuesHolder
) {

    private val viewWeakRef: WeakReference<View> = WeakReference(view)


    fun animateKeyPress(keyItemComponent: KeyItemComponent<CircularShape>) {
        ValueAnimator.ofArgb(
            keyPressAnimatorValuesHolder.keyComponentIdleColor,
            keyPressAnimatorValuesHolder.keyComponentPressedColor
        )
            .apply {
                duration = keyPressAnimatorValuesHolder.animDuration
                interpolator = DecelerateInterpolator()
                repeatMode = ValueAnimator.REVERSE
                repeatCount = 1
                addUpdateListener {
                    keyItemComponent.getPaint().color = it.animatedValue as Int
                    viewWeakRef.get()?.invalidate()
                }
            }.also {
                it.start()
            }


    }

}