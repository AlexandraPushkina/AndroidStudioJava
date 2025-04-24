package com.example.homework13;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> notes;
    private int[] styles = {R.style.NoteStyle_LightPink, R.style.NoteStyle_LightGreen, R.style.NoteStyle_LightBlue, R.style.NoteStyle_LightYellow};

    public NotesAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.titleTextView.setText(note.getTitle());
        holder.textTextView.setText(note.getText());

        int styleResId = styles[note.getStyle()];
        TypedArray ta = holder.itemView.getContext().obtainStyledAttributes(styleResId, new int[] {R.attr.noteBackgroundColor});
        int backgroundColor = ta.getColor(0, Color.WHITE);
        ta.recycle();

        holder.itemView.setBackgroundColor(backgroundColor);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView textTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.note_title);
            textTextView = itemView.findViewById(R.id.note_text);
        }
    }
}