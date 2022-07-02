package ir.test.firetv.ui.models

import android.content.Context
import android.view.View
import ir.huma.tvrecyclerview.lib.adapter.BaseRVBindingAdapter2
import ir.huma.tvrecyclerview.lib.adapter.BaseRVHolder
import ir.huma.tvrecyclerview.lib.adapter.BaseViewHolder
import ir.huma.tvrecyclerview.lib.adapter.BaseViewHolderItem
import ir.huma.tvrecyclerview.lib.interfaces.ItemSelectable
import ir.test.firetv.R
import ir.test.firetv.databinding.ItemRecyclerviewRowHumaBinding
import java.io.Serializable


@BaseViewHolder(BaseViewHolderItem(R.layout.item_recyclerview_row_huma, PlayListHolder::class))
data class Playlist(
    val id: String,
    val title: String,
    val videos: ArrayList<Video>
) : Serializable


class PlayListHolder(itemView: View, context: Context) : BaseRVHolder<Playlist>(itemView, context) {

    private lateinit var vids: ArrayList<Video>
    private val ada by lazy { BaseRVBindingAdapter2.create(context, vids) }

    override fun fill(t: Playlist, pos: Int, viewType: Int) {
        vids = t.videos
        (binding as ItemRecyclerviewRowHumaBinding).title.text = t.title
        (binding as ItemRecyclerviewRowHumaBinding).row.adapter = ada
        (binding as ItemRecyclerviewRowHumaBinding).row.selectLastPosition()
    }

}