package com.example.beautifultasks

import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.beautifultasks.databinding.ActivityEditBinding
import kotlin.random.Random

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private var taskIndex = 0
    private val adapter = TaskAdapter.taskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityEditBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initComponents()
        initButtons()

        if (intent.getIntExtra("position", -1) != -1){
            val taskToEdit = intent.getSerializableExtra("task") as Task
            binding.apply {
                imageView.setImageResource(taskToEdit.imageId)

                edTitle.setText(taskToEdit.name)
                edDesc.setText(taskToEdit.description)
                bDone.text = "Edit"

                val c = Color.parseColor(taskToEdit.color)
                bNext.setBackgroundColor(c)
                bDone.setBackgroundColor(c)
                drawer.setBackgroundColor(c)

                val t = Task.findTaskByColor(taskToEdit.color)
                edTitle.hint = t.first.name
                edDesc.hint = t.first.description

                taskIndex = t.second
            }
        }

        binding.editNV.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.deleteMonke -> {
                    adapter.deleteTask(intent.getIntExtra("position", 0))
                    finish()
                }
                R.id.cloneMonke -> {
                    adapter.addTask(formTask())
                }
                R.id.randomizeMonke -> {
                    taskIndex = Random.nextInt(0, 4)
                    initComponents()
                }
            }
            true
        }
    }

    private fun initComponents() {
        val task = Task.getTask(taskIndex)
        binding.apply {
            imageView.setImageResource(task.imageId)
            val c = Color.parseColor(task.color)
            bNext.setBackgroundColor(c)
            bDone.setBackgroundColor(c)
            drawer.setBackgroundColor(c)

            edTitle.hint = task.name
            edDesc.hint = task.description
        }
    }

    private fun addTask() = with(binding){
        val task = formTask()

        if (bDone.text == "Edit"){
            adapter.editTask(task, intent.getIntExtra("position", 0))
        } else {
            adapter.addTask(task)
        }
    }

    private fun formTask(): Task = with(binding){
        return Task(
            Task.getTask(taskIndex).imageId,
            stringOrHint(edTitle),
            stringOrHint(edDesc),
            Task.getTask(taskIndex).color
        )
    }

    private fun initButtons() = with(binding) {
        bNext.setOnClickListener {
            taskIndex = if (taskIndex + 1 < Task.getTaskCount()) (taskIndex + 1) else 0
            initComponents()
        }

        bDone.setOnClickListener {
            addTask()
            finish()
        }
    }

    private fun stringOrHint(e: EditText): String {
        if (e.text.toString() == ""){
            return e.hint.toString()
        }
        return e.text.toString()
    }
}