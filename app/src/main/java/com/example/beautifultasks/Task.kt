package com.example.beautifultasks

import java.io.Serializable

data class Task(
    var imageId: Int,
    var name: String,
    var description: String,
    var color: String
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
        fun findTaskByColor(c: String): Pair<Task, Int> {
            for (i in 0..tasks.size){
                if (tasks[i].color == c){
                    return Pair(tasks[i], i)
                }
            }
            return Pair(tasks.last(), tasks.size)
        }
    }
}