package com.jagerlipton.itservicetodolist.presentation.ui.completedtasks;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jagerlipton.itservicetodolist.databinding.HolderComletedTaskBinding;
import com.jagerlipton.itservicetodolist.presentation.OnItemClickListener;
import com.jagerlipton.itservicetodolist.presentation.model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompletedTasksListAdapter extends RecyclerView.Adapter<CompletedTasksListHolder> {

    @NonNull
    private final ArrayList<Task> completedTaskList = new ArrayList<>();
    private final OnItemClickListener onClickListener;
    private HolderComletedTaskBinding binding;

    public CompletedTasksListAdapter(OnItemClickListener onItemClickListener) {
        this.onClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CompletedTasksListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = HolderComletedTaskBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CompletedTasksListHolder(binding);
    }

    @Override
    public void onBindViewHolder(CompletedTasksListHolder holder, int position) {
        Task task = completedTaskList.get(position);
        holder.onBind(task, onClickListener);
    }

    @Override
    public int getItemCount() {
        return completedTaskList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addData(List<Task> data) {
        completedTaskList.clear();
        Collections.sort(data, new Comparator<Task>() {
            @Override
            public int compare(Task lhs, Task rhs) {
                if (lhs.getDateEnd() > rhs.getDateEnd()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        completedTaskList.addAll(data);
        notifyDataSetChanged();
    }
}

