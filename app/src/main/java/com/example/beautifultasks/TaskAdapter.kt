package com.example.beautifultasks

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beautifultasks.databinding.TaskItemBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {
    val taskList = ArrayList<Task>()

    class TaskHolder(item : View) : RecyclerView.ViewHolder(item) {
        val binding = TaskItemBinding.bind(item)

        fun bind(task: Task) = with(binding){
            im.setImageResource(task.imageId)
            tvTitle.text = task.name
            binding.tvTitle.setTextColor(Color.parseColor(task.color))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskHolder(view)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun addTask(task: Task){
        taskList.add(task)
        notifyItemInserted(taskList.size)
    }
}