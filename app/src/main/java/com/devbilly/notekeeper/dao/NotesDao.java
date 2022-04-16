package com.devbilly.notekeeper.dao;


import com.devbilly.notekeeper.model.Note;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NotesDao {

    //This is to allow to insert notes that might look identical
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note note);

    @Query("SELECT * FROM notes_table")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM notes_table WHERE id = :note_id")
    Note getNoteById(int note_id);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);
}
