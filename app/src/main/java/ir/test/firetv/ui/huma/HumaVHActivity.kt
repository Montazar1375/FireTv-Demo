package ir.test.firetv.ui.huma

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import ir.huma.tvrecyclerview.lib.adapter.BaseRVBindingAdapter2
import ir.huma.tvrecyclerview.lib.interfaces.OnItemSelectedListener
import ir.test.firetv.databinding.ActivityHumaVhActivityBinding
import ir.test.firetv.ui.models.Playlist
import ir.test.firetv.utils.DataGenerator

class HumaVHActivity : AppCompatActivity() {

    private val bind by lazy { ActivityHumaVhActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        val adapter = BaseRVBindingAdapter2.create(
            this,
            mutableListOf(
                Playlist("0", "Title 01", DataGenerator.videos(10)),
                Playlist("1", "Title 02", DataGenerator.videos(10)),
                Playlist("2", "Title 03", DataGenerator.videos(10)),
                Playlist("3", "Title 04", DataGenerator.videos(10)),
                Playlist("4", "Title 05", DataGenerator.videos(10)),
                Playlist("5", "Title 06", DataGenerator.videos(10)),
            ) as ArrayList<Playlist>,
        )
        bind.row.adapter = adapter


        bind.row.recycledViewPool.setMaxRecycledViews(0, 0);

        bind.row.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                position: Int,
                obj: Any?,
                v: RecyclerView.ViewHolder?,
                adapter: RecyclerView.Adapter<*>?
            ) {
                v?.itemView?.requestFocus()
            }
        }

    }
}