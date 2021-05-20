package com.example.humleporten.ROOMNoteCreating;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao

public interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY id DESC")
    List<Note> getAllNotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    @Delete
    void deleteNote(Note note);
}

/* Sources on ROOMNoteCreation from youtube videos on: https://www.youtube.com/playlist?list=PLam6bY5NszYN6-a1wt7yRISWfmYPdkbMu */