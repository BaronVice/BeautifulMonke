package com.example.beautifultasks

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beautifultasks.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = TaskAdapter.taskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()
    }

    private fun init(){
        adapter.setOnClickListener(object :
            TaskAdapter.OnClickListener {
                override fun onClick(position: Int, model: Task) {
                    val intent = Intent(this@MainActivity, EditActivity::class.java)
                    // Passing the data to the
                    // EmployeeDetails Activity
                    intent.putExtra("position", position)
                    intent.putExtra("task", model)
                    startActivity(intent)
                }
            }
        )

        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter = adapter
            addBtn.setOnClickListener{
                val intent = Intent(this@MainActivity, EditActivity::class.java)
                intent.putExtra("position", -1)
                startActivity(intent)
            }
        }
    }
}