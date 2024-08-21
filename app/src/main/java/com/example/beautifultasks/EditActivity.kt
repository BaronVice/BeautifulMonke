package com.example.beautifultasks

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.beautifultasks.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private var taskIndex = 0

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
                layout.setBackgroundColor(c)

                val t = Task.findTaskByColor(taskToEdit.color)
                edTitle.hint = t.first.name
                edDesc.hint = t.first.description

                taskIndex = t.second
            }
        }
    }

    private fun initComponents() {
        val task = Task.getTask(taskIndex)
        binding.apply {
            imageView.setImageResource(task.imageId)
            val c = Color.parseColor(task.color)
            bNext.setBackgroundColor(c)
            bDone.setBackgroundColor(c)
            layout.setBackgroundColor(c)

            edTitle.hint = task.name
            edDesc.hint = task.description
        }
    }

    private fun initButtons() = with(binding) {
        bNext.setOnClickListener {
            taskIndex = if (taskIndex + 1 < Task.getTaskCount()) (taskIndex + 1) else 0
            initComponents()
        }

        bDone.setOnClickListener {
            val task = Task(
                Task.getTask(taskIndex).imageId,
                stringOrHint(edTitle),
                stringOrHint(edDesc),
                Task.getTask(taskIndex).color
            )

            val editIntent = Intent().apply {
                putExtra("task", task)
                putExtra("position", intent.getIntExtra("position", -1))
            }
            setResult(RESULT_OK, editIntent)
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