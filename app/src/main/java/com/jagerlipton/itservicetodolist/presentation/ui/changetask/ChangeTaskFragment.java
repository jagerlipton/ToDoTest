package com.jagerlipton.itservicetodolist.presentation.ui.changetask;

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

import com.jagerlipton.itservicetodolist.databinding.FragmentChangeTaskBinding;
import com.jagerlipton.itservicetodolist.presentation.model.Task;
import com.jagerlipton.itservicetodolist.presentation.ui.Utils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ChangeTaskFragment extends Fragment {

    private FragmentChangeTaskBinding binding;
    private ChangeTaskViewModel changeTaskViewModel;
    private Observer<Task> currentTaskObserver;
    private Boolean changeFlag = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeTaskViewModel = new ViewModelProvider(this).get(ChangeTaskViewModel.class);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentChangeTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        currentTaskObserver = this::taskInflate;
        binding.buttonCancel.setOnClickListener(v -> button_cancel_click());
        binding.buttonSave.setOnClickListener(v -> button_ok_click());
        binding.buttonDone.setOnClickListener(v -> button_done_click());
    }

    @SuppressLint("SetTextI18n")
    private void taskInflate(Task task) {
        if (task != null) {
            if (task.getDateStart() != null) binding.dateStart.setText(Utils.longToDateTime(task.getDateStart()));
            if (task.getInfo() != null) binding.info.setText(task.getInfo());
            if (task.getStatus() != null) binding.status.setText(task.getStatus());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        changeTaskViewModel.getTask().observe(this, currentTaskObserver);
        if (getArguments() != null) {
            Integer id = getArguments().getInt("taskID");
            if (id != -1) {
                binding.buttonDone.setVisibility(View.VISIBLE);
                changeTaskViewModel.setTaskFromID(id);
                changeFlag = true;
            }
        } else {
            binding.buttonDone.setVisibility(View.GONE);
            changeTaskViewModel.setNewTask();
            changeFlag = false;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        changeTaskViewModel.getTask().removeObserver(currentTaskObserver);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void button_cancel_click() {
        NavHostFragment.findNavController(this).navigateUp();
    }

    private void button_ok_click() {
        if (changeFlag) {
            Task task = new Task();
            task.setInfo(binding.info.getText().toString());
            changeTaskViewModel.updateTask(task);
        } else {
            Task task = new Task();
            task.setInfo(binding.info.getText().toString());
            changeTaskViewModel.createNewTask(task);
        }
        NavHostFragment.findNavController(this).navigateUp();
    }

    private void button_done_click() {
        changeTaskViewModel.button_done_click();
        NavHostFragment.findNavController(this).navigateUp();
    }
}