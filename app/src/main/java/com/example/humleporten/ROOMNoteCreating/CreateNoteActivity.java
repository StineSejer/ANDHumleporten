package com.example.humleporten.ROOMNoteCreating;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.humleporten.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNoteActivity extends AppCompatActivity {
    private EditText noteTitle, noteDescription;
    private TextView textDateTime;
    private String selectedColor;
    private AlertDialog dialogDeleteNote;
    private Note alreadyAvailableNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        ImageView btBack = findViewById(R.id.backIcon);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        noteTitle = findViewById(R.id.noteTitle);
        noteDescription = findViewById(R.id.noteDescription);
        textDateTime = findViewById(R.id.textDateTime);

        textDateTime.setText(new SimpleDateFormat("EEEE, dd MMMM:mm a", Locale.getDefault()).format(new Date()));

        ImageView imageView = findViewById(R.id.saveNote);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        selectedColor = "#FFBB86FC";

        initMulti();

        if (getIntent().getBooleanExtra("isViewOrUpdate", false)) {
            alreadyAvailableNote = (Note) getIntent().getSerializableExtra(("note"));
            setViewOrUpdateNote();
        }

    }

    private void setViewOrUpdateNote() {
        noteTitle.setText(alreadyAvailableNote.getTitle());
        noteDescription.setText(alreadyAvailableNote.getNoteText());
        textDateTime.setText(alreadyAvailableNote.getDateTime());
    }

    private void saveNote() {
        if (noteTitle.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Write a title", Toast.LENGTH_SHORT).show();
            return;
        } else if (noteDescription.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Write description", Toast.LENGTH_SHORT).show();
            return;
        }
        final Note note = new Note();
        note.setTitle(noteTitle.getText().toString());
        note.setNoteText(noteDescription.getText().toString());
        note.setDateTime(textDateTime.getText().toString());
        note.setColor(selectedColor);

        if (alreadyAvailableNote != null) {
            note.setId(alreadyAvailableNote.getId());
        }
        @SuppressLint("StaticFieldLeak")
        class SaveNoteTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseNote.getDatabase(getApplicationContext()).noteDao().insertNote(note);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        }
        new SaveNoteTask().execute();
    }

    private void initMulti() {
        final LinearLayout layoutMulti = findViewById(R.id.layoutMulti);
        final BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(layoutMulti);
        layoutMulti.findViewById(R.id.text_multi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        final ImageView imageColor1 = layoutMulti.findViewById(R.id.imageColor1);
        final ImageView imageColor2 = layoutMulti.findViewById(R.id.imageColor2);
        final ImageView imageColor3 = layoutMulti.findViewById(R.id.imageColor3);

        layoutMulti.findViewById(R.id.viewColor1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColor = "#03DAC5";
                imageColor1.setImageResource(R.drawable.ic_check);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
            }
        });

        layoutMulti.findViewById(R.id.viewColor2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColor = "#FF0000";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(R.drawable.ic_check);
                imageColor3.setImageResource(0);
            }
        });
        layoutMulti.findViewById(R.id.viewColor3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColor = "#9F9F9F";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(R.drawable.ic_check);
            }
        });
        if (alreadyAvailableNote != null) {
            layoutMulti.findViewById(R.id.deleteNoteLayout).setVisibility(View.VISIBLE);
            layoutMulti.findViewById(R.id.deleteNoteLayout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    showDeleteNoteDialog();
                }
            });

        }
    }

    private void showDeleteNoteDialog() {
        if (dialogDeleteNote == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateNoteActivity.this);
            View view = LayoutInflater.from(this).inflate(
                    R.layout.layout_delete,
                    (ViewGroup) findViewById(R.id.layout_delete_comment)
            );

            builder.setView(view);
            dialogDeleteNote = builder.create();
            if (dialogDeleteNote.getWindow() != null) {
                dialogDeleteNote.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }

            view.findViewById(R.id.txDeleteNote).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    @SuppressLint("StaticFieldLeak")
                    class DeleteNoteTask extends AsyncTask<Void, Void, Void>{

                        @Override
                        protected Void doInBackground(Void... voids) {
                            DatabaseNote.getDatabase(getApplicationContext()).noteDao()
                                    .deleteNote(alreadyAvailableNote);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            Intent intent = new Intent();
                            intent.putExtra("isNoteDeleted", true);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }
                    new DeleteNoteTask().execute();

                }
            });
            view.findViewById(R.id.txCancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogDeleteNote.dismiss();
                }
            });
        }

        dialogDeleteNote.show();
    }

}

/*SOURCES
https://technobyte.org/create-notes-app-android-full-code-tutorial-simple-explanation/
* https://medium.com/swlh/simple-notes-app-in-android-java-9062d7bb3bc0
 https://www.youtube.com/watch?v=0cg09tlAAQ0&ab_channel=CodinginFlow
//  https://www.youtube.com/watch?v=hlkekoPqsis
 https://www.youtube.com/playlist?list=PLam6bY5NszYN6-a1wt7yRISWfmYPdkbMu*/