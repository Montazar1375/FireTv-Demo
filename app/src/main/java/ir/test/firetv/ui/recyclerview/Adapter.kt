package ir.test.firetv.ui.recyclerview

import android.os.Build
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import ir.test.firetv.R
import ir.test.firetv.databinding.ItemMovieBinding
import ir.test.firetv.databinding.ItemRecyclerviewRowBinding
import ir.test.firetv.ui.models.Playlist
import ir.test.firetv.ui.models.Video
import ir.test.firetv.utils.setFocusDPad


enum class ItemType(val layId: Int) {
    MOVIE(R.layout.item_movie),
    RECYCLERVIEW_ROW(R.layout.item_recyclerview_row);
}

data class MultipleItem(
    val itemT: ItemType,
    val content: Any? = null,
    override val itemType: Int = itemT.layId
) : MultiItemEntity

class Adapter : BaseMultiItemQuickAdapter<MultipleItem, BaseDataBindingHolder<*>>() {

    init {
        addItemType(ItemType.MOVIE.layId, R.layout.item_movie)
        addItemType(ItemType.RECYCLERVIEW_ROW.layId, R.layout.item_recyclerview_row)
    }

    private var onItemFocused: ((Int, Boolean, View, MultipleItem) -> Unit)? = null
    private var runForRowChanged: ((Int, View, MultipleItem) -> Unit)? = null

    fun setOnRowChange(onRowChange:((Int, View, MultipleItem) -> Unit)?){
        runForRowChanged = onRowChange
    }

    override fun convert(holder: BaseDataBindingHolder<*>, item: MultipleItem) {


        when (item.itemT) {
            ItemType.MOVIE -> (holder.dataBinding as ItemMovieBinding).let {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    it.root.focusable = View.FOCUSABLE
                }
                it.root.isFocusableInTouchMode = true


                it.video = item.content as Video?

                if ((getItemPosition(item) + 1) == itemCount) it.root.setFocusDPad(leftFocus = it.root)
                else if (getItemPosition(item) == 0) it.root.setFocusDPad(rightFocus = it.root)

                it.root.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
                    onItemFocused?.invoke(getItemPosition(item), hasFocus, v, item)

                    v.postDelayed({
                        v.z = if (hasFocus) 1000f else 0f
                    }, 100)

                    if (hasFocus) {
                        v.startAnimation(ScaleAnimation(
                            1f, 1.3f, 1f, 1.3f,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f
                        ).apply {
                            duration = 200
                            fillAfter = true
                            startOffset = 100
                        })
                    } else v.startAnimation(
                        ScaleAnimation(
                            1.3f, 1f, 1.3f, 1f,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f
                        ).apply {
                            duration = 200
                            fillAfter = true
                        })
                }

            }
            ItemType.RECYCLERVIEW_ROW -> (holder.dataBinding as ItemRecyclerviewRowBinding).let {
                val playList = item.content as Playlist
                val adapter = Adapter()

                it.title.text = playList.title

                adapter.onItemFocused = { s, f, g, ite ->
                    runForRowChanged?.invoke(getItemPosition(item), it.root, ite)
                }

                adapter.addData(playList.videos.map { vid -> MultipleItem(ItemType.MOVIE, vid) })

                it.row.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                it.row.adapter = adapter
            }
        }
    }
}