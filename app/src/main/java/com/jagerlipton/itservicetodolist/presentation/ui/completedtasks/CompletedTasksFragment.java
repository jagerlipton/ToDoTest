package com.jagerlipton.itservicetodolist.presentation.ui.completedtasks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jagerlipton.itservicetodolist.databinding.FragmentComletedTasksBinding;
import com.jagerlipton.itservicetodolist.presentation.OnItemClickListener;
import com.jagerlipton.itservicetodolist.presentation.model.Task;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CompletedTasksFragment extends Fragment {

    private FragmentComletedTasksBinding binding;
    private CompletedTasksViewModel completedTasksViewModel;
    private Observer<List<Task>> completedTasksListObserver;

    private final CompletedTasksListAdapter completedTasksListAdapter = new CompletedTasksListAdapter(new OnItemClickListener() {
        @Override
        public void onItemClick(Task task) {
            //
        }
    });

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        completedTasksViewModel = new ViewModelProvider(this).get(CompletedTasksViewModel.class);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentComletedTasksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.completedTasksRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.completedTasksRecyclerView.setAdapter(completedTasksListAdapter);
        completedTasksListObserver = completedTasksListAdapter::addData;
    }

    @Override
    public void onResume() {
        super.onResume();
        completedTasksViewModel.getCompletedTasksList().observe(this, completedTasksListObserver);
        completedTasksViewModel.getCompletedTasks();
    }

    @Override
    public void onPause() {
        super.onPause();
        completedTasksViewModel.getCompletedTasksList().removeObserver(completedTasksListObserver);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}