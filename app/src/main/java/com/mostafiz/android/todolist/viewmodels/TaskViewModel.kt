package com.mostafiz.android.todolist.viewmodels



import androidx.lifecycle.*
import com.mostafiz.android.todolist.daos.TaskDao
import com.mostafiz.android.todolist.models.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TaskViewModel @Inject constructor(private val dao: TaskDao): ViewModel() {


//    fun insert(text: CharSequence) = ioThread {
//        dao.insert(TaskModel(id = 0, name = text.toString()))
//    }
//
//    fun remove(cheese: TaskModel) = ioThread {
//        dao.delete(cheese)
//    }
//

    fun insertTask(taskModel: TaskModel) {
        CoroutineScope(IO).launch {
            dao.insertModel(taskModel)
        }

    }

    fun getTaskList():LiveData<List<TaskModel>> {
        return  dao.getAllModelLive()
    }



    fun updateStatus(id:Int,status:Boolean) {
        CoroutineScope(IO).launch {
            dao.updateModel(id,status)
        }
    }

}
