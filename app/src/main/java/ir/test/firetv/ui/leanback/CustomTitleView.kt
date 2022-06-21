package ir.test.firetv.ui.leanback

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.InputDevice
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.leanback.widget.TitleViewAdapter
import ir.test.firetv.R
import ir.test.firetv.databinding.LaySimpleTitleBinding
import ir.test.firetv.utils.setFocusDPad

class CustomTitleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), TitleViewAdapter.Provider {
    init {
        LayoutInflater.from(context).inflate(R.layout.lay_simple_title, this)
    }

    private val bind by lazy { LaySimpleTitleBinding.bind(findViewById(R.id.root)) }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        //fix focus direction
        bind.card1.setFocusDPad(bind.card2)
        bind.card2.setFocusDPad(bind.card3)
        bind.card3.setFocusDPad(bind.card4)
        bind.card4.setFocusDPad(bind.card5)
    }


    private val mTitleViewAdapter: TitleViewAdapter = object : TitleViewAdapter() {
        override fun getSearchAffordanceView(): View {
            return rootView
        }

        override fun setTitle(titleText: CharSequence?) {
            this@CustomTitleView.setTitle(titleText)
        }

        override fun setOnSearchClickedListener(listener: OnClickListener?) {
        }

        override fun updateComponentsVisibility(flags: Int) {

            if ((flags and BRANDING_VIEW_VISIBLE) == BRANDING_VIEW_VISIBLE) {
                updateBadgeVisibility(true)
            } else {
                bind.textTitle.visibility = View.GONE
            }

        }

        private fun updateBadgeVisibility(visible: Boolean) {
            if (visible) {
                bind.textTitle.visibility = VISIBLE
            } else {
                bind.textTitle.visibility = GONE
            }
        }
    }

    fun setTitle(title: CharSequence?) {
        if (title != null) {
            bind.textTitle.text = title
            bind.textTitle.visibility = VISIBLE
        }
    }

    override fun getTitleViewAdapter(): TitleViewAdapter {
        return mTitleViewAdapter
    }


}