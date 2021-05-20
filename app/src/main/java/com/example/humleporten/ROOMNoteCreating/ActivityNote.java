package com.example.humleporten.ROOMNoteCreating;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.humleporten.BeerLibrary.PlayBtnActivity;
import com.example.humleporten.MainScreen.BeerActivity;
import com.example.humleporten.MainScreen.MainActivity;
import com.example.humleporten.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ActivityNote extends AppCompatActivity implements ListenerNote {
    public static final int REQUEST_CODE_AND_NOTE = 1;
    public static final int REQUEST_CODE_UPDATE_NOTE = 2;
    public static final int REQUEST_CODE_SHOW_NOTES = 3;

    private RecyclerView aNotesRecyclerView;
    private List<Note> noteList;
    private AdapterNote adapterNote;
    private int noteClickedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

      /*  //navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_header);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()) {
                    case R.id.beerLibrarybt:
                        startActivity(new Intent(getApplicationContext()
                                , BeerActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.beerBrewing:
                        startActivity(new Intent(getApplicationContext()
                                , PlayBtnActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;

            }
        });*/

        ImageView imageAddNoteMain = findViewById(R.id.button_note_add);
        imageAddNoteMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                startActivityForResult(new Intent(getApplicationContext(), CreateNoteActivity.class), REQUEST_CODE_AND_NOTE);
            }

        });
        aNotesRecyclerView = findViewById(R.id.aNotesRecyclerView);
        aNotesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        noteList = new ArrayList<>();
        adapterNote = new AdapterNote(noteList, this);
        aNotesRecyclerView.setAdapter(adapterNote);

        getNotes(REQUEST_CODE_SHOW_NOTES, false);

        EditText searchNotes = findViewById(R.id.searchNotes);
        searchNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                adapterNote.cancelTimer();

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (noteList.size() != 0) {
                    adapterNote.searchNotes(searchNotes.toString());
                }

            }
        });


    }

    @Override
    public void onNoteClicked(Note note, int position) {
        noteClickedPosition = position;
        Intent intent = new Intent(getApplicationContext(), CreateNoteActivity.class);
        intent.putExtra("isViewOrUpdate", true);
        intent.putExtra("note", note);
        startActivityForResult(intent, REQUEST_CODE_UPDATE_NOTE);
    }

    private void getNotes(final int requestCode, final boolean isNoteDeleted) {

        @SuppressLint("StaticFieldLeak")
        class GetNotesTask extends AsyncTask<Void, Void, List<Note>> {
            @Override
            protected List<Note> doInBackground(Void... voids) {
                return DatabaseNote
                        .getDatabase(getApplicationContext()).noteDao().getAllNotes();
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);
                if (requestCode == REQUEST_CODE_SHOW_NOTES) {
                    noteList.addAll(notes);
                    adapterNote.notifyDataSetChanged();
                } else if (requestCode == REQUEST_CODE_AND_NOTE) {
                    noteList.add(0,notes.get(0));
                    adapterNote.notifyItemInserted(0);
                    aNotesRecyclerView.smoothScrollToPosition(0);
                } else if (requestCode == REQUEST_CODE_UPDATE_NOTE) {
                    noteList.remove(noteClickedPosition);
                    if (isNoteDeleted) {
                        adapterNote.notifyItemRemoved(noteClickedPosition);
                    } else {
                        noteList.add(noteClickedPosition, notes.get(noteClickedPosition));
                        adapterNote.notifyItemChanged(noteClickedPosition);
                    }
                }
            }
        }
        new GetNotesTask().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_AND_NOTE && requestCode == RESULT_OK) {
            getNotes(REQUEST_CODE_AND_NOTE, false);
        } else if (requestCode == REQUEST_CODE_UPDATE_NOTE && resultCode == RESULT_OK) {
            if (data != null) {
                getNotes(REQUEST_CODE_UPDATE_NOTE, data.getBooleanExtra("isNoteDeleted", false));
            }
        }
    }
}


/* Sources on ROOMNoteCreation from youtube videos on: https://www.youtube.com/playlist?list=PLam6bY5NszYN6-a1wt7yRISWfmYPdkbMu
https://www.youtube.com/watch?v=0cg09tlAAQ0&ab_channel=CodinginFlow
https://www.youtube.com/watch?v=hlkekoPqsis
 */