package com.mostafiz.android.todolist.utils

interface CheckListener <Int,Boolean,MaterialTextView>{
    fun onChecked(id:Int,status:Boolean,tv:MaterialTextView)
}