package com.jagerlipton.itservicetodolist.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.jagerlipton.itservicetodolist.data.db.model.TaskDB;
import java.util.List;


@Dao
public interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)  // добавить новую задачу
    void insertTask(TaskDB task);

    @Update
    int updateTask(TaskDB task); // обновить задачу

    @Query("SELECT * FROM task_table WHERE status = 'NEW'") // получить список всех новых дел
    List<TaskDB> getNewTaskList();

    @Query("SELECT * FROM task_table WHERE status = 'DONE'") // получить список всех выполненных дел
    List<TaskDB> getDoneTaskList();

    @Query("SELECT * FROM task_table") // получить список всех дел
    List<TaskDB> getAllTaskList();

    @Query("SELECT * FROM task_table WHERE id = :taskID")  // получить задачу по ID
    TaskDB getTaskFromID(int taskID);

    @Query("SELECT COUNT(id) FROM task_table")  // получить количество всех задач
    int getAllCount();

    @Query("SELECT COUNT(id) FROM task_table WHERE status = 'NEW'")  // получить количество новых задач
    int getNewCount();

    @Query("SELECT COUNT(id) FROM task_table WHERE status = 'DONE'")  // получить количество выполненных задач
    int getCompletedCount();

    @Query("SELECT MIN(dateEnd - dateStart) FROM task_table WHERE status = 'DONE'")  // получить минимальное время задачи
    long getMinTime();

    @Query("SELECT MAX(dateEnd - dateStart) FROM task_table WHERE status = 'DONE'")  // получить максимальное время задачи
    long getMaxTime();

    @Query("SELECT AVG(dateEnd - dateStart) FROM task_table WHERE status = 'DONE'")  // получить среднее время задачи
    long getAverageTime();

    @Query("SELECT id, MIN(dateEnd - dateStart) FROM task_table WHERE status = 'DONE'")  // получить id задачи с минимальным временем
    int getMinTimeID();

    @Query("SELECT id, MAX(dateEnd - dateStart) FROM task_table WHERE status = 'DONE'")  // получить id задачи с максимальным временем
    int getMaxTimeID();

}