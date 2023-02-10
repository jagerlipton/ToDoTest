package com.jagerlipton.itservicetodolist.presentation.ui.currenttasks;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jagerlipton.itservicetodolist.databinding.HolderTaskBinding;
import com.jagerlipton.itservicetodolist.presentation.OnItemClickListener;
import com.jagerlipton.itservicetodolist.presentation.model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CurrentTasksListAdapter extends RecyclerView.Adapter<CurrentTasksListHolder> {

    @NonNull
    private final ArrayList<Task> currentTasksList = new ArrayList<>();
    private final OnItemClickListener onClickListener;
    private HolderTaskBinding binding;

    public CurrentTasksListAdapter(OnItemClickListener onItemClickListener) {
        this.onClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CurrentTasksListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = HolderTaskBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CurrentTasksListHolder(binding);
    }

    @Override
    public void onBindViewHolder(CurrentTasksListHolder holder, int position) {
        Task task = currentTasksList.get(position);
        holder.onBind(task, onClickListener);
    }

    @Override
    public int getItemCount() {
        return currentTasksList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addData(List<Task> data) {
        currentTasksList.clear();
        Collections.sort(data, new Comparator<Task>() {
            @Override
            public int compare(Task lhs, Task rhs) {
                if (lhs.getDateStart() > rhs.getDateStart()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        currentTasksList.addAll(data);
        notifyDataSetChanged();
    }
}

