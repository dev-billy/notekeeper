package com.devbilly.notekeeper.repository;

import android.app.Application;

import com.devbilly.notekeeper.dao.NotesDao;
import com.devbilly.notekeeper.model.Note;
import com.devbilly.notekeeper.model.NotesRoomDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NotesRepository {

    private NotesDao mNotesDao;
    private LiveData<List<Note>> mAllNotes;


    public NotesRepository(Application application){
        NotesRoomDatabase db = NotesRoomDatabase.getDatabase(application);
        mNotesDao = db.notesDao();
        mAllNotes = mNotesDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes(){
        return mAllNotes;
    }


    public void insert(Note note) {
        NotesRoomDatabase.databaseWriteExecutor.execute(() -> {
            mNotesDao.insert(note);
        });
    }

    public void update(Note note) {
        NotesRoomDatabase.databaseWriteExecutor.execute(() ->{
            mNotesDao.updateNote(note);
        });
    }

    public void delete(Note note){
        NotesRoomDatabase.databaseWriteExecutor.execute(() ->{
            mNotesDao.deleteNote(note);
        });
    }
}
