package com.echolabs.custompassword.item_view

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import com.dionisiofilho.passwordview.R


class ItemEdittextPassword(context: Context) : EditText(context) {

    init {
        inputType = InputType.TYPE_CLASS_NUMBER
        transformationMethod = PasswordTransformationMethod.getInstance()

        LinearLayout.LayoutParams(100, 100).also { lp ->
            lp.setMargins(12, 12, 12, 12)
            lp.gravity = Gravity.CENTER
            layoutParams = lp
        }
        setPadding(6, 6, 6, 6)


        setBackgroundResource(R.drawable.background_password_custom)
        keyListener = DigitsKeyListener.getInstance("0123456789")
        isFocusable = false
        isFocusableInTouchMode = true
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        setLines(1)
        maxLines = 1
        val fArray = arrayOfNulls<InputFilter>(1)
        fArray[0] = InputFilter.LengthFilter(1)
        filters = fArray
    }
}
