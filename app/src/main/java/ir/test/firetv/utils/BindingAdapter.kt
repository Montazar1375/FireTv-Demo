package ir.test.firetv.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory

val fadeFactory: DrawableCrossFadeFactory =
    DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

@BindingAdapter("srcUrl", "srcPlaceHolder", "srcFade", requireAll = false)
fun ImageView.setImage(
    srcUrl: String?, srcPlaceHolder: Drawable? = null, srcFade: Boolean? = null
) {
    if (srcUrl.isNullOrEmpty())
        Glide.with(this).clear(this)
    else
        Glide.with(this).load(srcUrl).apply {
            var s = this
            if (srcFade != false)
                s = transition(DrawableTransitionOptions.withCrossFade(fadeFactory))
            if (srcPlaceHolder != null) s.placeholder(srcPlaceHolder).into(this@setImage)
            else s.into(this@setImage)
        }
}

@BindingAdapter("scaleOnFocus", "scaleOnFocusValue", requireAll = false)
fun View.scaleOnFocus(scaleOnFocus: Boolean, scaleOnFocusValue: Float? = 1.1f) {

    if (scaleOnFocus) {
        val s = scaleOnFocusValue ?: 1.1f

        setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) startAnimation(
                ScaleAnimation(
                    1f, s, 1f, s,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
                ).apply {
                    duration = 200
                    fillAfter = true
                })
            else startAnimation(
                ScaleAnimation(
                    s, 1f, s, 1f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
                ).apply {
                    duration = 200
                    fillAfter = true
                })
        }
    }
}

