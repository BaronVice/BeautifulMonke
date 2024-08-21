package com.example.beautifultasks

import java.io.Serializable

data class Task(
    val imageId: Int,
    val name: String,
    val description: String,
    val color: String
) : Serializable {
    companion object{
        private val tasks = listOf(
            Task(R.drawable.m1,"Fance Monke","Heehee","#de0945"),
            Task(R.drawable.m2,"Busines Monke","HaHa","#f007ec"),
            Task(R.drawable.m3,"Hapee Monke","HooHoo","#1164bd"),
            Task(R.drawable.m4,"Hypsta Monke","HuaHua","#ff8c08")
        )

        fun getTaskCount() = tasks.size
        fun getTask(i: Int) = tasks[i]
    }
}