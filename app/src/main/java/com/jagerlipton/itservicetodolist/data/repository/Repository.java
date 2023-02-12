package com.jagerlipton.itservicetodolist.data.repository;

import androidx.sqlite.db.SimpleSQLiteQuery;

import com.jagerlipton.itservicetodolist.data.db.TaskDAO;
import com.jagerlipton.itservicetodolist.data.db.model.TaskDB;
import com.jagerlipton.itservicetodolist.presentation.model.Task;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {

    private final TaskDAO taskDAO;

    public Repository(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    public List<Task> getCurrentTasks() {
        List<Task> currentTaskList = new ArrayList<>();
      //  List<TaskDB> BDTaskList = taskDAO.getNewTaskList();
        List<TaskDB> BDTaskList = taskDAO.getNewTaskListSorted();
        currentTaskList = TaskDB.mapTaskDBListToTaskList(BDTaskList);
        return currentTaskList;
    }

    @Override
    public List<Task> getCompletedTasks() {
        List<Task> currentTaskList = new ArrayList<>();
       // List<TaskDB> BDTaskList = taskDAO.getDoneTaskList();
        List<TaskDB> BDTaskList = taskDAO.getDoneTaskListSorted();
        currentTaskList = TaskDB.mapTaskDBListToTaskList(BDTaskList);
        return currentTaskList;
    }


    @Override
    public Task getTaskFromID(Integer index) {
        return TaskDB.mapTaskDBToTask(taskDAO.getTaskFromID(index));
    }

    @Override
    public Integer getCompletedTaskCount() {
        return taskDAO.getCompletedCount();
    }

    @Override
    public Long getAverageCompleteTime() {
        return taskDAO.getAverageTime();
    }

    @Override
    public Long getMinCompleteTime() {
        return taskDAO.getMinTime();
    }

    @Override
    public Long getMaxCompleteTime() {
        return taskDAO.getMaxTime();
    }

    @Override
    public void updateTask(Task task) {
        taskDAO.updateTask(TaskDB.mapTaskToTaskDB(task));
    }

    @Override
    public void createTask(Task task) {
        taskDAO.insertTask(TaskDB.mapTaskToTaskDB(task));
    }

    @Override
    public Integer getMinTimeID() {
        return taskDAO.getMinTimeID();
    }

    @Override
    public Integer getMaxTimeID() {
        return taskDAO.getMaxTimeID();
    }

    @Override
    public Long getCompletedTaskCountRaw() {
           return  taskDAO.sendRawQuery(new SimpleSQLiteQuery("SELECT COUNT(id) FROM task_table WHERE status = 'DONE'"));
    }

    @Override
    public Long getAverageCompleteTimeRaw() {
        return  taskDAO.sendRawQuery(new SimpleSQLiteQuery("SELECT AVG(dateEnd - dateStart) FROM task_table WHERE status = 'DONE'"));
    }

    @Override
    public Long getMinCompleteTimeRaw() {
        return  taskDAO.sendRawQuery(new SimpleSQLiteQuery("SELECT MIN(dateEnd - dateStart) FROM task_table WHERE status = 'DONE'"));
    }

    @Override
    public Long getMaxCompleteTimeRaw() {
        return  taskDAO.sendRawQuery(new SimpleSQLiteQuery("SELECT MAX(dateEnd - dateStart) FROM task_table WHERE status = 'DONE'"));
    }
}
