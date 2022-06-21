package ir.test.firetv.ui.models

import java.io.Serializable

data class Playlist(
    val id: String,
    val title: String,
    val videos:ArrayList<Video>
) : Serializable