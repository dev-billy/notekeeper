package com.devbilly.notekeeper.viewModel;

import android.app.Application;

import com.devbilly.notekeeper.model.Note;
import com.devbilly.notekeeper.repository.NotesRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NotesViewModel extends AndroidViewModel {

    private NotesRepository mNotesRepository;
    private LiveData<List<Note>>  mAllNotes;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        mNotesRepository = new NotesRepository(application);
        mAllNotes = mNotesRepository.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes(){
        return mAllNotes;
    }


    public void addNote(Note note) {
        mNotesRepository.insert(note);
    }

    public void updateNote(Note note) {
        mNotesRepository.update(note);
    }

    public void deleteNote(Note note) {
        mNotesRepository.delete(note);
    }
}
