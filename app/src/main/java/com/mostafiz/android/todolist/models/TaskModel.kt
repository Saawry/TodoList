package com.mostafiz.android.todolist.models
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Keep
@Entity
class TaskModel {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "description")
    var description: String? = null
    @ColumnInfo(name = "date")
    var date: String? = null
    @ColumnInfo(name = "status")
    var status: Boolean

    constructor(description: String?, date: String?, status: Boolean) {
        this.description = description
        this.date = date
        this.status = status
    }

    @Ignore
    constructor(id: Int, description: String?, date: String?, status: Boolean) {
        this.id = id
        this.description = description
        this.date = date
        this.status = status
    }

    override fun toString(): String {
        return "TaskModel(id=$id, description=$description, date=$date, status=$status)"
    }


}
