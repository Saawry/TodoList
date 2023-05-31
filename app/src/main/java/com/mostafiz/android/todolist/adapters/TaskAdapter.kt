package com.mostafiz.android.todolist.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.mostafiz.android.todolist.R
import com.mostafiz.android.todolist.databinding.CardTaskItemBinding
import com.mostafiz.android.todolist.models.TaskModel
import com.mostafiz.android.todolist.utils.CheckListener
import javax.inject.Inject

class TaskAdapter @Inject constructor() :
    RecyclerView.Adapter<TaskAdapter.EmployeeViewHolder>() {

    var modelList = mutableListOf<TaskModel>()
    private var clickInterface: CheckListener<Int, Boolean, MaterialTextView>? = null
    private lateinit var context: Context
    fun updateModelList(modelList: List<TaskModel>) {
        this.modelList = modelList.toMutableList()
        notifyItemRangeInserted(0, modelList.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding =
            CardTaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return EmployeeViewHolder(binding)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {

        val model = modelList[position]
        holder.view.taskNameTv.text=model.description

        Log.d("StatusCheck", "onBindViewHolder: "+model.toString())
        if (!model.status){
            Log.d("StatusCheck", "onBindViewHolder: "+model.toString())
            holder.view.markedCheckbox.isChecked=true
            holder.view.taskNameTv.setTextColor(context.getColor(R.color.grey))
            holder.view.taskNameTv.paintFlags = holder.view.taskNameTv.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }else{
            Log.d("StatusCheck", "onBindViewHolder else: "+model.toString())
            holder.view.markedCheckbox.isChecked=false
            holder.view.taskNameTv.setTextColor(context.getColor(R.color.black))
            holder.view.taskNameTv.paintFlags = holder.view.taskNameTv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }


        holder.view.markedCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            clickInterface?.onChecked(model.id, isChecked,holder.view.taskNameTv)
        }


    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setItemClick(clickInterface: CheckListener<Int,Boolean,MaterialTextView>) {
        this.clickInterface = clickInterface
    }

    class EmployeeViewHolder(val view: CardTaskItemBinding) : RecyclerView.ViewHolder(view.root)
}

