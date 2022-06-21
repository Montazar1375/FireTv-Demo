package ir.test.firetv.ui.leanback

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.bumptech.glide.request.transition.Transition
import ir.test.firetv.ui.models.Video
import ir.test.firetv.utils.DataGenerator
import jp.wasabeef.glide.transformations.BlurTransformation

class LeanBackHomeFragment : BrowseSupportFragment() {

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View? {
        isHeadersTransitionOnBackEnabled = true

        setupRows()

        return super.onCreateView(i, c, s)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        headersState = HEADERS_DISABLED

        title = "برنامه تلقزیونی قهقرا"

        view.layoutDirection = View.LAYOUT_DIRECTION_RTL

        setOnItemViewSelectedListener { _, item, _, _ ->
            if (item is Video) {

                val fadeFactory: DrawableCrossFadeFactory =
                    DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

                Glide.with(this)
                    .load(item.imgUrl)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade(fadeFactory))
                    .apply(RequestOptions.bitmapTransform(BlurTransformation(2, 3)))
                    .into<CustomTarget<Drawable>>(object : CustomTarget<Drawable>() {
                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable>?
                        ) {
                            view.background = resource.apply { alpha = 100 }
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                            view.background = placeholder
                        }
                    })
            }
        }
    }


    private fun setupRows() {
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter()

        for (i in 1 until 10) {
            val listRowAdapter = ArrayObjectAdapter(cardPresenter)
            val videos = DataGenerator.videos(20)
            videos.forEach { video -> listRowAdapter.add(video) }
            val header = HeaderItem(rowsAdapter.size().toLong(), "ردیف شماره $i")
            rowsAdapter.add(ListRow(header, listRowAdapter))
        }

        adapter = rowsAdapter
    }


}