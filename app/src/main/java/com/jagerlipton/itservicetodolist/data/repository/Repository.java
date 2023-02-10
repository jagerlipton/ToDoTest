package com.jagerlipton.itservicetodolist.data.repository;

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
        List<TaskDB> BDTaskList = taskDAO.getNewTaskList();
        currentTaskList = TaskDB.mapTaskDBListToTaskList(BDTaskList);
        return currentTaskList;
    }

    @Override
    public List<Task> getCompletedTasks() {
        List<Task> currentTaskList = new ArrayList<>();
        List<TaskDB> BDTaskList = taskDAO.getDoneTaskList();
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
}
