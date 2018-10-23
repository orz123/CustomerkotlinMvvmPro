package com.orz.kotlin_mvvm_demo.widget

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.EditText


/**
 * 自定义文本输入框，增加清空按钮
 *
 * @author  orz
 */
class CustomEditText : EditText {

    private var mLeft: Drawable? = null
    private var mTop: Drawable? = null
    private var mRight: Drawable? = null
    private var mBottom: Drawable? = null

    private var mBounds: Rect? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        setDrawable()
        // 增加文本监听器.
        addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                setDrawable()
            }
        })
    }

    // 输入框右边的图标显示控制
    private fun setDrawable() {
        if (length() == 0) {
            setCompoundDrawables(mLeft, mTop, null, mBottom)
        } else {
            setCompoundDrawables(mLeft, mTop, mRight, mBottom)
        }
    }

    override fun setCompoundDrawables(left: Drawable?, top: Drawable?, right: Drawable?, bottom: Drawable?) {
        if (mLeft == null) {
            this.mLeft = left
        }
        if (mTop == null) {
            this.mTop = top
        }
        if (mRight == null) {
            this.mRight = right
        }
        if (mBottom == null) {
            this.mBottom = bottom
        }
        super.setCompoundDrawables(left, top, right, bottom)
    }

    // 输入事件处理
    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (mRight != null && event.action == MotionEvent.ACTION_UP) {
            this.mBounds = mRight!!.bounds
            mRight!!.intrinsicWidth
            val eventX = event.x.toInt()
            val width = mBounds!!.width()
            val right = right
            val left = left
            if (eventX > right - width * 2 - left) {
                setText("")
                event.action = MotionEvent.ACTION_CANCEL
            }
        }
        return super.onTouchEvent(event)
    }

}
