package com.jagerlipton.itservicetodolist.presentation.ui.completedtasks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jagerlipton.itservicetodolist.data.repository.IRepository;
import com.jagerlipton.itservicetodolist.presentation.model.Task;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CompletedTasksViewModel extends ViewModel {
    private IRepository repository;
    private final MutableLiveData<List<Task>> completedTasksList = new MutableLiveData<>();
    public LiveData<List<Task>> getCompletedTasksList() {
        return completedTasksList;
    }

    @Inject
    public CompletedTasksViewModel(IRepository repository) {
        this.repository = repository;
    }
    public void getCompletedTasks() {
        completedTasksList.setValue(repository.getCompletedTasks());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }
}
