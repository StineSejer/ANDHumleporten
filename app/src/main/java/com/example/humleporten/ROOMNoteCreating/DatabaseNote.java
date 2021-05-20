package com.example.humleporten.ROOMNoteCreating;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class DatabaseNote extends RoomDatabase {
    private static DatabaseNote databaseNote;

    public static synchronized DatabaseNote getDatabase(Context context) {
        if ((databaseNote == null)) {
            databaseNote = Room.databaseBuilder(context, DatabaseNote.class, "db_notes").build();
        }
        return databaseNote;
    }

    public abstract NoteDao noteDao();
}


/*SOURCE: https://developer.android.com/reference/androidx/room/RoomDatabase
https://developer.android.com/reference/androidx/room/DatabaseConfiguration
https://developer.android.com/reference/androidx/room/RoomDatabase.Builder
 https://www.youtube.com/playlist?list=PLam6bY5NszYN6-a1wt7yRISWfmYPdkbMu*/