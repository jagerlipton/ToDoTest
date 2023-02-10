package com.jagerlipton.itservicetodolist.presentation.ui.currenttasks;

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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jagerlipton.itservicetodolist.R;
import com.jagerlipton.itservicetodolist.databinding.FragmentCurrentTasksBinding;
import com.jagerlipton.itservicetodolist.presentation.OnItemClickListener;
import com.jagerlipton.itservicetodolist.presentation.model.Task;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CurrentTasksFragment extends Fragment {

    private FragmentCurrentTasksBinding binding;
    private CurrentTasksViewModel currentTasksViewModel;
    private Observer<List<Task>> currentTasksListObserver;

    private final CurrentTasksListAdapter currentTasksListAdapter = new CurrentTasksListAdapter(new OnItemClickListener() {
        @Override
        public void onItemClick(Task task) {
            changeTaskFragment(task);
        }
    });

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentTasksViewModel = new ViewModelProvider(this).get(CurrentTasksViewModel.class);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentCurrentTasksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.currentTasksRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.currentTasksRecyclerView.setAdapter(currentTasksListAdapter);
        currentTasksListObserver = currentTasksListAdapter::addData;
    }

    @Override
    public void onResume() {
        super.onResume();
        currentTasksViewModel.getCurrentTasksList().observe(this, currentTasksListObserver); // старт наблюдения за списком текущих дел во вьюмодели
        currentTasksViewModel.getCurrentTasks(); // запрос в репозиторий списка текущих тасок
    }

    @Override
    public void onPause() {
        super.onPause();
        currentTasksViewModel.getCurrentTasksList().removeObserver(currentTasksListObserver); // отписка от наблюдения
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;  // обнуление биндинга во избежание утечек
    }

    private void changeTaskFragment(Task task) {
        Bundle bundle = new Bundle();
        bundle.putInt("taskID", task.getId());
        NavHostFragment.findNavController(this).navigate(R.id.action_CurrentTasks_to_ChangeTasks, bundle);
    }
}