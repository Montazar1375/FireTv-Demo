package ir.test.firetv.utils

import android.view.KeyEvent
import android.view.View


fun View.setFocusDPad(
    rightFocus: View? = null,
    leftFocus: View? = null,
    upFocus: View? = null,
    downFocus: View? = null
) {

    setOnKeyListener { _, keyCode, event ->

        var handled = false

        if (event.action != KeyEvent.ACTION_UP) {
            if (rightFocus != null && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                rightFocus.requestFocus()
                handled = true
            } else if (leftFocus != null && keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                leftFocus.requestFocus()
                handled = true
            } else if (upFocus != null && keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                upFocus.requestFocus()
                handled = true
            } else if (downFocus != null && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                downFocus.requestFocus()
                handled = true
            }
        }

        return@setOnKeyListener handled
    }
}