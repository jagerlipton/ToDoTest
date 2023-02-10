package com.jagerlipton.itservicetodolist.presentation.ui.currenttasks;

import androidx.recyclerview.widget.RecyclerView;

import com.jagerlipton.itservicetodolist.databinding.HolderTaskBinding;
import com.jagerlipton.itservicetodolist.presentation.OnItemClickListener;
import com.jagerlipton.itservicetodolist.presentation.model.Task;
import com.jagerlipton.itservicetodolist.presentation.ui.Utils;

public class CurrentTasksListHolder extends RecyclerView.ViewHolder {
    private HolderTaskBinding binding;

    public CurrentTasksListHolder(HolderTaskBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void onBind(Task task, OnItemClickListener onClickListener) {
        binding.dateTextView.setText(Utils.longToDateTime(task.getDateStart()));
        binding.InfoTextView.setText(task.getInfo());
        if (onClickListener != null) {
            itemView.setOnClickListener(v -> onClickListener.onItemClick(task));
        }
    }
}
