package com.jagerlipton.itservicetodolist.presentation.ui.info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jagerlipton.itservicetodolist.data.repository.IRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class InfoViewModel extends ViewModel {
    private IRepository repository;

    private final MutableLiveData<Long> completeCount = new MutableLiveData<>();
    public LiveData<Long> getCompleteCount() {
        return completeCount;
    }

    private final MutableLiveData<Long> minTime = new MutableLiveData<>();
    public LiveData<Long> getMinTime() {
        return minTime;
    }

    private final MutableLiveData<Long> maxTime = new MutableLiveData<>();
    public LiveData<Long> getMaxTime() {
        return maxTime;
    }

    private final MutableLiveData<Long> averageTime = new MutableLiveData<>();
    public LiveData<Long> getAverageTime() {
        return averageTime;
    }

    @Inject
    public InfoViewModel(IRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository = null;
    }

    public void getInfo() {
      //  completeCount.setValue(repository.getCompletedTaskCount());
        completeCount.setValue(repository.getCompletedTaskCountRaw());

      //  minTime.setValue(repository.getMinCompleteTime());
        minTime.setValue(repository.getMinCompleteTimeRaw());

     //   maxTime.setValue(repository.getMaxCompleteTime());
        maxTime.setValue(repository.getMaxCompleteTimeRaw());

     // averageTime.setValue(repository.getAverageCompleteTime());
        averageTime.setValue(repository.getAverageCompleteTimeRaw());
    }

    public Integer min_time_click() {
        return repository.getMinTimeID();
    }

    public Integer max_time_click() {
        return repository.getMaxTimeID();
    }

}
