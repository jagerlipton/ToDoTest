package com.jagerlipton.itservicetodolist.presentation.ui.completedtasks;

import androidx.recyclerview.widget.RecyclerView;

import com.jagerlipton.itservicetodolist.databinding.HolderComletedTaskBinding;
import com.jagerlipton.itservicetodolist.presentation.OnItemClickListener;
import com.jagerlipton.itservicetodolist.presentation.model.Task;
import com.jagerlipton.itservicetodolist.presentation.ui.Utils;

public class CompletedTasksListHolder extends RecyclerView.ViewHolder {
    private HolderComletedTaskBinding binding;

    public CompletedTasksListHolder(HolderComletedTaskBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void onBind(Task task, OnItemClickListener onClickListener) {
        binding.dateStartTextView.setText(Utils.longToDateTime(task.getDateStart()));
        binding.dateEndTextView.setText(Utils.longToDateTime(task.getDateEnd()));
        binding.InfoTextView.setText(task.getInfo());
        if (onClickListener != null) {
            itemView.setOnClickListener(v -> onClickListener.onItemClick(task));
        }
    }
}