package com.mostafiz.android.todolist.storage

import org.junit.After
import org.junit.Before
import org.junit.Test
import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.mostafiz.android.todolist.daos.TaskDao
import com.mostafiz.android.todolist.models.TaskModel

import junit.framework.TestCase
import kotlinx.coroutines.runBlocking

import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class AppDatabaseTest :TestCase(){
    private lateinit var db: AppDatabase
    private lateinit var dao: TaskDao


    @Before
    public override fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.getTaskDao()
    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertRetrieveTask(): Unit = runBlocking {
        val task = TaskModel("Test Task","2023-05-31T05:41:00.000",false)
        dao.insertModel(task)
        val tasks = dao.getAllModel()
        assertThat(tasks.contains(task))
    }
}