package com.example.humleporten.ROOMNoteCreating;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.humleporten.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class AdapterNote extends RecyclerView.Adapter<AdapterNote.NoteViewHolder>
{
    private List<Note> notes;
    private final ListenerNote notesListener;
    private Timer newTimer;
    private final List<Note> sourceNote;

    public AdapterNote(List<Note> notes, ListenerNote notesListener){
        this.notes = notes;
        this.notesListener = notesListener;
        sourceNote = notes;
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_note_jar,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, final int position) {
        holder.setNote(notes.get(position));
        holder.noteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesListener.onNoteClicked(notes.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount(){return notes.size();}

    @Override
    public int getItemViewType(int position){return position;}

    static class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView txTitle, txDateTime;
        LinearLayout noteLayout;

        public NoteViewHolder(@NonNull View itemView){
            super(itemView);
            txTitle = itemView.findViewById(R.id.txTitle);
            txDateTime = itemView.findViewById(R.id.txDateTime);
            noteLayout = itemView.findViewById(R.id.layoutNote);
        }

        void setNote(Note note){
            txTitle.setText(note.getTitle());
            txDateTime.setText(note.getDateTime());

            Drawable drawable = (Drawable) noteLayout.getBackground().mutate();
            Drawable wrappedDrawable = DrawableCompat.wrap(drawable);

            if(note.getColor() !=null){
                System.out.println(note.getColor());
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor(note.getColor()));
            }
            else {
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#ff6600"));
            }
        }

    }

    public void searchNotes(final String searchKeyword){
        newTimer= new Timer();
        newTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (searchKeyword.trim().isEmpty()) {
                    notes = sourceNote;
                } else {
                    ArrayList<Note> temp = new ArrayList<>();
                    for (Note note : sourceNote) {
                        if (note.getTitle().toLowerCase().contains(searchKeyword.toLowerCase())
                                || note.getNoteText().toLowerCase().contains(searchKeyword.toLowerCase())) {
                            temp.add(note);
                        }
                    }
                    notes = temp;
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            }
        }, 300);
    }
    public void cancelTimer(){
        if(newTimer != null){
            newTimer.cancel();
        }
    }

}

/* Sources on ROOMNoteCreation from youtube videos on: https://www.youtube.com/playlist?list=PLam6bY5NszYN6-a1wt7yRISWfmYPdkbMu */