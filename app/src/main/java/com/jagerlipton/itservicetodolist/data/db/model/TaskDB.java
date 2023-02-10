package com.jagerlipton.itservicetodolist.data.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.jagerlipton.itservicetodolist.presentation.model.Task;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "task_table")

public class TaskDB {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "dateStart")
    public long dateStart;

    @ColumnInfo(name = "dateEnd")
    public long dateEnd;

    @ColumnInfo(name = "info")
    public String info;

    @ColumnInfo(name = "status")
    public String status;

    public TaskDB(int id, long dateStart, long dateEnd, String info, String status) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.info = info;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public long getDateStart() {
        return dateStart;
    }

    public long getDateEnd() {
        return dateEnd;
    }

    public String getInfo() {
        return info;
    }

    public String getStatus() {
        return status;
    }

    public static TaskDB mapTaskToTaskDB(Task input) {
        return new TaskDB(input.getId(), input.getDateStart(), input.getDateEnd(), input.getInfo(), input.getStatus());
    }

    public static Task mapTaskDBToTask(TaskDB input) {
        return new Task(input.getId(), input.getDateStart(), input.getDateEnd(), input.getInfo(), input.getStatus());
    }

    public static List<Task> mapTaskDBListToTaskList(List<TaskDB> input) {
        List<Task> output = new ArrayList<>();
        for (TaskDB task : input) {
            output.add(mapTaskDBToTask(task));
        }
        return output;
    }

    @Override
    public String toString() {
        return "TaskDB{" +
                "id=" + id +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", info='" + info + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
