package com.example.beautifultasks

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.beautifultasks.databinding.TaskItemBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {
    private val taskList = ArrayList<Task>()
    private lateinit var onClickListener: OnClickListener

    companion object{
        val taskAdapter = TaskAdapter()
    }


    class TaskHolder(item : View, private val onClickListener: OnClickListener) : RecyclerView.ViewHolder(item) {
        private val binding = TaskItemBinding.bind(item)

        fun bind(task: Task) = with(binding){
            im.setImageResource(task.imageId)
            tvTitle.text = task.name
            binding.tvTitle.setTextColor(Color.parseColor(task.color))

            this@TaskHolder.itemView.setOnClickListener{
                onClickListener.onClick(position, task) // ??
            }
        }
    }

    interface OnClickListener {
        fun onClick(position: Int, model: Task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskHolder(view, onClickListener)
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.onClickListener = listener
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val task = taskList[position]
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun addTask(task: Task){
        taskList.add(task)
        notifyItemInserted(taskList.size)
    }

    fun editTask(task: Task, pos: Int){
        taskList[pos] = task
        notifyItemChanged(pos)
    }

    fun deleteTask(pos: Int){
        taskList.removeAt(pos)
        notifyItemRemoved(pos)
    }
}