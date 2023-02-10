package com.jagerlipton.itservicetodolist.presentation.ui.changetask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jagerlipton.itservicetodolist.data.repository.IRepository;
import com.jagerlipton.itservicetodolist.presentation.model.Task;
import com.jagerlipton.itservicetodolist.presentation.ui.Utils;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ChangeTaskViewModel extends ViewModel {
    private IRepository repository;

    @Inject
    public ChangeTaskViewModel(IRepository repository) {
        this.repository = repository;
    }
    private final MutableLiveData<Task> currentTask = new MutableLiveData<>();
    public LiveData<Task> getTask() {
        return currentTask;
    }

    public void setTaskFromID(Integer index) {
        currentTask.setValue(repository.getTaskFromID(index));
    }

    public void setNewTask() {
        Task task = new Task();
        task.setDateStart(Utils.getTimeNow());
        task.setDateEnd(0L);
        task.setInfo("");
        task.setStatus("NEW");
        currentTask.setValue(task);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }

    public void createNewTask(Task task) {
        task.setId(0);
        task.setDateStart(Utils.getTimeNow());
        task.setDateEnd(0L);
        task.setStatus("NEW");
        repository.createTask(task);
    }

    public void updateTask(Task task) {
        Task oldTask = currentTask.getValue();
        oldTask.setInfo(task.getInfo());
        repository.updateTask(oldTask);
    }

    public void button_done_click() {
        Task task = currentTask.getValue();
        task.setStatus("DONE");
        task.setDateEnd(Utils.getTimeNow());
        repository.updateTask(task);
    }

}
