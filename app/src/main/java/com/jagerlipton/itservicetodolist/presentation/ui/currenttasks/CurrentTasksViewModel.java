package com.jagerlipton.itservicetodolist.presentation.ui.currenttasks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jagerlipton.itservicetodolist.data.repository.IRepository;
import com.jagerlipton.itservicetodolist.presentation.model.Task;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CurrentTasksViewModel extends ViewModel {
    private IRepository repository;
    private final MutableLiveData<List<Task>> currenTasksList = new MutableLiveData<>();
    public LiveData<List<Task>> getCurrentTasksList() {
        return currenTasksList;
    }

    @Inject
    public CurrentTasksViewModel(IRepository repository) {
        this.repository = repository;
    }
    public void getCurrentTasks() {
        currenTasksList.setValue(repository.getCurrentTasks());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }
}
