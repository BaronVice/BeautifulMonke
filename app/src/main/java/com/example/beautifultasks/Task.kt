package com.example.beautifultasks

data class Task(
    val imageId: Int,
    val name: String,
    val color: String
) {
    companion object{
        private val imageIds = listOf(
            R.drawable.m1,
            R.drawable.m2,
            R.drawable.m3,
            R.drawable.m4,
        )
        private val names = listOf(
            "Fance Monke",
            "Busines Monke",
            "Hapee Monke",
            "Hypsta Monke"
        )
        private val colors = listOf(
            "#de0945",
            "#f007ec",
            "#1164bd",
            "#ff8c08"
        )

        fun getTask(num: Int): Task{
            return Task(
                imageIds[num],
                names[num],
                colors[num]
            )
        }
    }
}