package com.devbilly.notekeeper.model;


import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "notes_table")
public class Note implements Serializable {

    @PrimaryKey (autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo (name = "title")
    private  String title;

    @NonNull
    @ColumnInfo (name = "update_date")
    private  String updateDate;

    @Nullable
    private  String notesValue;

    public Note(@NonNull String title, @NonNull String updateDate, @Nullable String notesValue) {
        this.title = title;
        this.updateDate = updateDate;
        this.notesValue = notesValue;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getUpdateDate() {
        return updateDate;
    }

    @Nullable
    public String getNotesValue() {
        return notesValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
       this.id = id;
    }

    public void setUpdateDate(@NonNull String updateDate) {
        this.updateDate = updateDate;
    }

    public void setNotesValue(@Nullable String notesValue) {
        this.notesValue = notesValue;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }
}
