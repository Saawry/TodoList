package com.mostafiz.android.todolist.activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textview.MaterialTextView
import com.mostafiz.android.todolist.R
import com.mostafiz.android.todolist.adapters.TaskAdapter
import com.mostafiz.android.todolist.databinding.ActivityMainBinding
import com.mostafiz.android.todolist.databinding.DialogAddTaskBinding
import com.mostafiz.android.todolist.models.TaskModel
import com.mostafiz.android.todolist.utils.CheckListener
import com.mostafiz.android.todolist.utils.WrapContentLinearLayoutManager
import com.mostafiz.android.todolist.viewmodels.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)



    private val taskViewModel: TaskViewModel by viewModels()

    @Inject
    lateinit var taskAdapter : TaskAdapter

    private lateinit var addTaskDialog:AlertDialog
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.taskRecycler.apply {
            layoutManager= WrapContentLinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter=taskAdapter
        }

        taskAdapter.setItemClick(object :
            CheckListener< Int, Boolean,MaterialTextView> {
            @SuppressLint("NotifyDataSetChanged", "ResourceAsColor")
            override fun onChecked(id: Int, status: Boolean,tv: MaterialTextView) {
                updateStatus(id,status)
//                if (status){
//                    tv.setTextColor(R.color.grey)
//                    tv.paintFlags = tv.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
//                }else{
//                    tv.setTextColor(R.color.black)
//                    tv.paintFlags = tv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
//                }
            }
        })



        taskViewModel.getTaskList().observe(this) {
            Log.d("Datachanges", "onCreate:  "+it.toString())
            taskAdapter.updateModelList(it)
        }


        binding.addTaskBtn.setOnClickListener{
            showAddTaskDialog()
        }
    }

    private fun updateStatus(id: Int, status: Boolean) {
        taskViewModel.updateStatus(id,status)
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
    }

    private fun showAddTaskDialog() {
        val dialogBuilder =
            AlertDialog.Builder(this)
        val dialogBinding: DialogAddTaskBinding =
            DialogAddTaskBinding.inflate(LayoutInflater.from(this), null, false)
        dialogBuilder.setView(dialogBinding.root)


        dialogBinding.closeImg.setOnClickListener {
            addTaskDialog.dismiss()
        }


        dialogBinding.btnInsert.setOnClickListener {
            val taskName = dialogBinding.taskNameEt.text.toString()
            if (taskName.isEmpty()) {
                dialogBinding.taskNameEt.error = "empty"
                return@setOnClickListener
            } else {
                dialogBinding.taskNameEt.error = null
                insertTask(taskName)
            }

        }


        addTaskDialog = dialogBuilder.create()

        addTaskDialog.show()
    }

    private fun insertTask(taskName: String) {
        val model=TaskModel(taskName,dateFormat.format(System.currentTimeMillis()),false)
        taskViewModel.insertTask(model)
        addTaskDialog.dismiss()
    }

}