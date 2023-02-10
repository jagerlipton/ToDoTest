package com.jagerlipton.itservicetodolist.data.repository;

import com.jagerlipton.itservicetodolist.presentation.model.Task;

import java.util.List;

public interface IRepository {
    List<Task> getCurrentTasks();

    List<Task> getCompletedTasks();

    Task getTaskFromID(Integer index);

    Integer getCompletedTaskCount();

    Long getAverageCompleteTime();

    Long getMinCompleteTime();

    Long getMaxCompleteTime();

    Integer getMinTimeID();

    Integer getMaxTimeID();

    void updateTask(Task task);

    void createTask(Task task);
}
