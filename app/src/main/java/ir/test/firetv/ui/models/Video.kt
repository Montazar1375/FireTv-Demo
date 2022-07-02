package ir.test.firetv.ui.models

import android.content.Context
import android.view.View
import android.widget.Toast
import ir.huma.tvrecyclerview.lib.adapter.BaseRVHolder
import ir.huma.tvrecyclerview.lib.adapter.BaseViewHolder
import ir.huma.tvrecyclerview.lib.adapter.BaseViewHolderItem
import ir.huma.tvrecyclerview.lib.interfaces.ItemSelectable
import ir.test.firetv.R
import ir.test.firetv.databinding.ItemMovieBinding
import java.io.Serializable

@BaseViewHolder(BaseViewHolderItem(R.layout.item_movie, VideoHolder::class))
data class Video(
    val id: String = "",
    val title: String? = null,
    val des: String? = null,
    val imgUrl: String? = null,
    val channel: String? = null
) : Serializable


class VideoHolder(itemView: View, context: Context) : BaseRVHolder<Video>(itemView, context),
    ItemSelectable {

    override fun fill(t: Video, pos: Int, viewType: Int) {
        (binding as ItemMovieBinding).video = t
    }

    override fun changeSelected(isSelected: Boolean, focus: Boolean, pos: Int, obj: Any?) {
        Toast.makeText(context, "dsf $pos", Toast.LENGTH_SHORT).show()
    }
}