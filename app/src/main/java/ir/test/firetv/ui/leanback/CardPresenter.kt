package ir.test.firetv.ui.leanback

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import ir.test.firetv.R
import ir.test.firetv.ui.models.Video

class CardPresenter : Presenter() {

    private var selectedBackgroundColor: Int = 0
    private var defaultBackgroundColor: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        parent?.context?.let { context ->
            defaultBackgroundColor = ContextCompat.getColor(context, R.color.default_background)
            selectedBackgroundColor = ContextCompat.getColor(context, R.color.selected_background)
        }

        val cardView = object : ImageCardView(parent?.context) {
            override fun setSelected(selected: Boolean) {
                updateCardBackgroundColor(this, selected)
                super.setSelected(selected)
            }
        }

        cardView.isFocusable = true
        cardView.isFocusableInTouchMode = true
        updateCardBackgroundColor(cardView, false)
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val video = (item as Video)

        val cardView = viewHolder?.view as ImageCardView
        cardView.titleText = video.title
        cardView.contentText = video.channel
        cardView.setMainImageDimensions(350, 180)

        val fadeFactory: DrawableCrossFadeFactory =
            DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

        Glide.with(cardView.context).load(video.imgUrl).placeholder(R.drawable.movie)
            .transition(DrawableTransitionOptions.withCrossFade(fadeFactory))
            .centerCrop().into(cardView.mainImageView)

    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        val cardView = viewHolder?.view as ImageCardView
        cardView.badgeImage = null
        cardView.mainImage = null
    }


    private fun updateCardBackgroundColor(view: ImageCardView, selected: Boolean) {
        val color = if (selected) selectedBackgroundColor else defaultBackgroundColor
        view.setBackgroundColor(color)
        view.setInfoAreaBackgroundColor(color)
    }
}