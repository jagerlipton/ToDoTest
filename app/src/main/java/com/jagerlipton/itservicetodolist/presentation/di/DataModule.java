package com.jagerlipton.itservicetodolist.presentation.di;

import android.app.Application;

import androidx.room.Room;

import com.jagerlipton.itservicetodolist.data.db.DataBase;
import com.jagerlipton.itservicetodolist.data.db.TaskDAO;
import com.jagerlipton.itservicetodolist.data.repository.IRepository;
import com.jagerlipton.itservicetodolist.data.repository.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DataModule {

    @Provides
    @Singleton
    IRepository provideRepository(TaskDAO taskDAO) {
        return new Repository(taskDAO);
    }

    @Provides
    @Singleton
    public static DataBase provideDB(Application application) {
        return Room.databaseBuilder(application, DataBase.class, "db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static TaskDAO provideTaskDAO(DataBase appDatabase) {
        return appDatabase.getTaskDAO();
    }


}
























