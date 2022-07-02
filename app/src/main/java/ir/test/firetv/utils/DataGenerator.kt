package ir.test.firetv.utils

import ir.test.firetv.ui.models.Playlist
import ir.test.firetv.ui.models.Video
import kotlin.random.Random

object DataGenerator {
    fun videos(count: Int): ArrayList<Video> {
        val vids = ArrayList<Video>()
        for (i in 1 until count) {
            val imgRand = Random.nextInt(1, 100)
            vids.add(
                Video(
                    i.toString(),
                    "عنوان فیلم $i",
                    "نمونه آزمایشی ردیف شماره $i",
                    "https://picsum.photos/200?random=$imgRand",
                    "5"
                )
            )
        }
        return vids
    }

    fun playLists(count: Int): ArrayList<Playlist> {
        val playLists = ArrayList<Playlist>()

        for (i in 1 until count) {
            playLists.add(Playlist(i.toString(), "ردیف شماره $i", videos(10)))
        }

        return playLists
    }
}