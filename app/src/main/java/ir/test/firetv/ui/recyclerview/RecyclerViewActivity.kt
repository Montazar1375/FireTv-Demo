package ir.test.firetv.ui.recyclerview

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import ir.test.firetv.databinding.ActivityRecyclerViewBinding
import ir.test.firetv.ui.models.Video
import ir.test.firetv.utils.DataGenerator
import ir.test.firetv.utils.fadeFactory
import jp.wasabeef.glide.transformations.BlurTransformation

class RecyclerViewActivity : AppCompatActivity() {

    private val bind by lazy { ActivityRecyclerViewBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        bind.header.textTitle.text = "برنامه تلقزیونی قهقرا"

        val adapter = Adapter()
        adapter.setOnRowChange { row, _, selectedVideo ->
            bind.appBarLayout.setExpanded(row == 0)

            Glide.with(this)
                .load((selectedVideo.content as Video).imgUrl)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(fadeFactory))
                .apply(RequestOptions.bitmapTransform(BlurTransformation(2, 3)))
                .into<CustomTarget<Drawable>>(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(
                        resource: Drawable, transition: Transition<in Drawable>?
                    ) {
                        bind.root.background = resource.apply { alpha = 100 }
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        bind.root.background = placeholder
                    }
                })
        }

        adapter.addData(
            DataGenerator.playLists(10).map { MultipleItem(ItemType.RECYCLERVIEW_ROW, it) }
        )

        bind.mainList.layoutManager = LinearLayoutManager(this)
        bind.mainList.adapter = adapter

    }
}