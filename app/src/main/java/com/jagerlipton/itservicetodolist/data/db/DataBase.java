package com.jagerlipton.itservicetodolist.data.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.jagerlipton.itservicetodolist.data.db.model.TaskDB;

@Database(entities = {TaskDB.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract TaskDAO getTaskDAO();
}
