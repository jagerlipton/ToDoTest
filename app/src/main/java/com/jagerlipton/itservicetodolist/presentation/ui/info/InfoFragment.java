package com.jagerlipton.itservicetodolist.presentation.ui.info;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.jagerlipton.itservicetodolist.R;
import com.jagerlipton.itservicetodolist.databinding.FragmentInfoBinding;
import com.jagerlipton.itservicetodolist.presentation.ui.Utils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InfoFragment extends Fragment {

    private FragmentInfoBinding binding;
    private InfoViewModel infoViewModel;
    private Observer<Long> completeCountObserver;
    private Observer<Long> minTimeObserver;
    private Observer<Long> maxTimeObserver;
    private Observer<Long> averageTimeObserver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        infoViewModel = new ViewModelProvider(this).get(InfoViewModel.class);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        completeCountObserver = this::setTextCompleteCount;
        minTimeObserver = this::setTextMinTime;
        maxTimeObserver = this::setTextMaxTime;
        averageTimeObserver = this::setTextAverageTime;
        binding.minTime.setOnClickListener(v -> min_time_click());
        binding.maxTime.setOnClickListener(v -> max_time_click());
        binding.minTimeLabel.setOnClickListener(v -> min_time_click());
        binding.maxTimeLabel.setOnClickListener(v -> max_time_click());
    }

    @Override
    public void onResume() {
        super.onResume();
        infoViewModel.getCompleteCount().observe(this, completeCountObserver);
        infoViewModel.getMinTime().observe(this, minTimeObserver);
        infoViewModel.getMaxTime().observe(this, maxTimeObserver);
        infoViewModel.getAverageTime().observe(this, averageTimeObserver);
        infoViewModel.getInfo();
    }

    @Override
    public void onPause() {
        super.onPause();
        infoViewModel.getCompleteCount().removeObserver(completeCountObserver);
        infoViewModel.getMinTime().removeObserver(minTimeObserver);
        infoViewModel.getMaxTime().removeObserver(maxTimeObserver);
        infoViewModel.getAverageTime().removeObserver(averageTimeObserver);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("SetTextI18n")
    private void setTextCompleteCount(Long count) {
        binding.completedCount.setText(count.toString());
    }

    @SuppressLint("SetTextI18n")
    private void setTextMinTime(long minTime) {
        binding.minTime.setText(Utils.longToTime(minTime));
    }

    @SuppressLint("SetTextI18n")
    private void setTextMaxTime(long maxTime) {
        binding.maxTime.setText(Utils.longToTime(maxTime));
    }

    @SuppressLint("SetTextI18n")
    private void setTextAverageTime(long averageTime) {
        binding.averageTime.setText(Utils.longToTime(averageTime));
    }

    private void min_time_click() {
        Bundle bundle = new Bundle();
        bundle.putInt("taskID", infoViewModel.min_time_click());
        NavHostFragment.findNavController(this).navigate(R.id.action_InfoTasks_to_ChangeTasks, bundle);
    }

    private void max_time_click() {
        Bundle bundle = new Bundle();
        bundle.putInt("taskID", infoViewModel.max_time_click());
        NavHostFragment.findNavController(this).navigate(R.id.action_InfoTasks_to_ChangeTasks, bundle);
    }
}