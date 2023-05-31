package com.mostafiz.android.todolist.daos


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mostafiz.android.todolist.models.TaskModel


@Dao
interface TaskDao {
    @Insert
    fun insertModel( model: TaskModel)

    @Query("SELECT * FROM TaskModel")
    fun getAllModelLive(): LiveData<List<TaskModel>>

    @Query("SELECT * FROM TaskModel")
    fun getAllModel(): List<TaskModel>



    @Query("UPDATE TaskModel set status=:status where id =:id")
    fun updateModel(id:Int, status:Boolean)

    @Delete
    fun deleteModel(model: TaskModel)
}