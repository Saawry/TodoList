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
    var id: Long = 0
    @ColumnInfo(name = "description")
    var description: String? = null
    @ColumnInfo(name = "date")
    var createdAt: String? = null
    @ColumnInfo(name = "status")
    var status: Boolean

    constructor(description: String?, createdAt: String?, status: Boolean) {
        this.description = description
        this.createdAt = createdAt
        this.status = status
    }

    @Ignore
    constructor(id: Long, description: String?, createdAt: String?, status: Boolean) {
        this.id = id
        this.description = description
        this.createdAt = createdAt
        this.status = status
    }
}
