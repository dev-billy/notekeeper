package com.devbilly.notekeeper.model;

import android.content.Context;

import com.devbilly.notekeeper.dao.NotesDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = Note.class, version = 2, exportSchema = false)
public abstract class NotesRoomDatabase extends RoomDatabase {

    public abstract NotesDao notesDao();

    private static volatile NotesRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    // to control the thread pool database actions could be performed asynchronously in the background
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);



    public static NotesRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotesRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NotesRoomDatabase.class, "notes_database")
                                    .build();
                }
            }

        }
        return INSTANCE;
    }



}
