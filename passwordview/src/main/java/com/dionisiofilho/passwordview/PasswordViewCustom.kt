package com.dionisiofilho.passwordview

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.echolabs.custompassword.extensions.hideKeyboard
import com.echolabs.custompassword.extensions.showKeyboard
import com.echolabs.custompassword.item_view.ItemEdittextPassword
import com.echolabs.custompassword.utils.VibrateUtils

class PasswordViewCustom : LinearLayout {

    private var qtdPassword: Int = 4
    private var textPassword: StringBuilder = StringBuilder()
    private var finishPassword: ((String) -> Unit?)? = null

    constructor(context: Context) : super(context) {
        start(null)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        start(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(context, attributeSet, defStyle) {
        start(attributeSet)
    }


    private fun start(attributeSet: AttributeSet?) {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        attributeSet?.let {

            val ta = context.obtainStyledAttributes(attributeSet, R.styleable.ItemPasswordCustom)

            try {
                qtdPassword = ta.getInteger(R.styleable.ItemPasswordCustom_qtd_password, 4)

                if (qtdPassword > 8) {
                    throw Exception("Quantity password invalid, max. 8")
                }

                mountViewPassword()

            } catch (e: Exception) {
                Log.i("ItemEdittext", e.toString(), e)
            } finally {
                ta.recycle()
            }
        }


    }

    fun getQtdPassword(): Int {
        return qtdPassword
    }

    private fun mountViewPassword() {

        var value = 0

        while (value != qtdPassword) {
            value = value.inc()

            val ed = ItemEdittextPassword(context)

            when (value) {

                1 -> {
                    ed.id = R.id.digito1
                }

                2 -> {
                    ed.id = R.id.digito2
                }

                3 -> {
                    ed.id = R.id.digito3
                }

                4 -> {
                    ed.id = R.id.digito4
                }

                5 -> {
                    ed.id = R.id.digito5
                }

                6 -> {
                    ed.id = R.id.digito6
                }

                7 -> {
                    ed.id = R.id.digito7
                }

                8 -> {
                    ed.id = R.id.digito8
                }


            }
            ed.addTextChangedListener(focus(ed))
            backKeyListener(ed)
            addView(ed)
        }

    }

    private fun nextFocus(ed: ItemEdittextPassword) {

        textPassword.append(ed.text.toString())

        when (ed.id) {

            R.id.digito1 -> {
                findViewById<ItemEdittextPassword>(R.id.digito2)?.requestFocus()
            }

            R.id.digito2 -> {
                findViewById<ItemEdittextPassword>(R.id.digito3)?.requestFocus()
            }

            R.id.digito3 -> {
                findViewById<ItemEdittextPassword>(R.id.digito4)?.requestFocus()
            }

            R.id.digito4 -> {
                findViewById<ItemEdittextPassword>(R.id.digito5)?.requestFocus() ?: run {
                    ed.clearFocus()
                    finishDigitPassword()
                }
            }

            R.id.digito5 -> {
                findViewById<ItemEdittextPassword>(R.id.digito6)?.requestFocus() ?: run {
                    ed.clearFocus()
                    finishDigitPassword()
                }
            }

            R.id.digito6 -> {
                findViewById<ItemEdittextPassword>(R.id.digito7)?.requestFocus() ?: run {
                    ed.clearFocus()
                    finishDigitPassword()
                }
            }

            R.id.digito7 -> {
                findViewById<ItemEdittextPassword>(R.id.digito8)?.requestFocus() ?: run {
                    ed.clearFocus()
                    finishDigitPassword()
                }
            }

            R.id.digito8 -> {
                ed.clearFocus()
                finishDigitPassword()
            }

        }
    }

    private fun finishDigitPassword() {

        if (context is Activity) {
            (context as Activity).hideKeyboard()
        } else if (context is FragmentActivity) {
            (context as FragmentActivity).hideKeyboard()
        }

        this.finishPassword?.let {
            it.invoke(textPassword.toString())
        }

    }

    private fun showKeyboard() {
        if (context is Activity) {
            (context as Activity).showKeyboard()
        } else if (context is FragmentActivity) {
            (context as FragmentActivity).showKeyboard()
        }
    }

    private fun focus(ed: ItemEdittextPassword): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                s?.let { editable ->

                    if (editable.length == 1) {
                        nextFocus(ed)
                    }
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.i("", "")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("", "")
            }
        }
    }

    private fun clearAllEdittext() {
        for (i in 0 until childCount) {
            val v = getChildAt(i)
            if (v is ItemEdittextPassword) {
                v.text.clear()
            }
        }

        textPassword.clear()

        findViewById<ItemEdittextPassword>(R.id.digito1)?.also {
            it.requestFocus()
        }

    }

    private fun backKeyListener(ed: ItemEdittextPassword) {
        ed.setOnKeyListener(object : OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {

                event?.let { ev ->

                    if (keyCode == 67 && ev.action == KeyEvent.ACTION_UP) {
                        clearAllEdittext()
                        return true
                    } else if (ed.length() == 1 && ev.action == KeyEvent.ACTION_UP) {
                        nextFocus(ed)
                    }
                }

                return false
            }
        })
    }

    fun onFinishPassword(onFinish: (password: String) -> Unit?) {
        this.finishPassword = onFinish
    }

    fun getTextPassowrd(): String {
        return textPassword.toString()
    }

    fun error() {
        VibrateUtils.vibrate(context)
        for (i in 0 until childCount) {
            val v = getChildAt(i)
            if (v is ItemEdittextPassword) {
                if (v.text.isNullOrEmpty()) {
                    v.background =
                        ContextCompat.getDrawable(context, R.drawable.background_password_custom_error)
                }
            }
        }
    }

    fun cleanError() {
        for (i in 0 until childCount) {
            val v = getChildAt(i)
            if (v is ItemEdittextPassword) {
                if (v.text.isNotEmpty()) {
                    v.background =
                        ContextCompat.getDrawable(context, R.drawable.background_password_custom)
                }
            }
        }
    }

    fun startFocus() {
        findViewById<ItemEdittextPassword>(R.id.digito1)?.requestFocus()
        showKeyboard()
    }
}