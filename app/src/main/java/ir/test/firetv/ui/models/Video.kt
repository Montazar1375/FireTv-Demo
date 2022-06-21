package ir.test.firetv.ui.models

import java.io.Serializable

data class Video(
    val id: String = "",
    val title: String? = null,
    val des: String? = null,
    val imgUrl: String? = null,
    val channel: String? = null
) : Serializable