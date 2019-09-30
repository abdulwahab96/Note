package com.abdulhadi.abdulwahab.notes;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

  /*  private static final Object LOCK = new Object();
    private static AppDatabase sInstance;


    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "Note")
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return sInstance;
    }*/

    public abstract NoteDao noteDao();
}