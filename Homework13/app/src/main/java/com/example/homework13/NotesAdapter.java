package com.example.homework13;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import androidx.appcompat.widget.PopupMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private static final int EDIT_NOTE_REQUEST_CODE = 2;
    private List<Note> notes;
    private final int[] styles = {
            R.style.NoteStyle_LightPink,
            R.style.NoteStyle_LightGreen,
            R.style.NoteStyle_LightBlue,
            R.style.NoteStyle_LightYellow
    };

    public NotesAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.titleTextView.setText(note.getTitle());
        holder.textTextView.setText(note.getText());

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Clicked: " + note.getTitle(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(v.getContext(), CreateNoteActivity.class);
            intent.putExtra("note", note);
            intent.putExtra("note_position", position);

            if (v.getContext() instanceof MainActivity) {
                ((MainActivity) v.getContext()).launchEditNoteActivity(note, position);
            } else {
                ((Activity) v.getContext()).startActivityForResult(
                        intent, EDIT_NOTE_REQUEST_CODE);
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            Log.d("NotesAdapter", "Long click at position " + position);
            Toast.makeText(v.getContext(), "Long click: " + note.getTitle(), Toast.LENGTH_SHORT).show();
            showPopupMenu(v, position);
            return true;
        });


        int styleResId = styles[note.getStyle()];
        TypedArray ta = holder.itemView.getContext().obtainStyledAttributes(
                styleResId, new int[] {R.attr.noteBackgroundColor});
        int backgroundColor = ta.getColor(0, Color.WHITE);
        ta.recycle();

        holder.itemView.setBackgroundColor(backgroundColor);
    }

    private void showPopupMenu(View view, int position) {
        Log.d("NotesAdapter", "Showing popup menu for position " + position);

        PopupMenu popup = new PopupMenu(view.getContext(), view);
        popup.getMenuInflater().inflate(R.menu.note_context_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            Log.d("NotesAdapter", "Menu item clicked: " + id);

            if (id == R.id.action_edit) {

                Toast.makeText(view.getContext(), "Edit note", Toast.LENGTH_SHORT).show();
                Note noteToEdit = notes.get(position);
                Intent intent = new Intent(view.getContext(), CreateNoteActivity.class);
                intent.putExtra("note", noteToEdit);
                intent.putExtra("note_position", position);

                if (view.getContext() instanceof MainActivity) {
                    ((MainActivity) view.getContext()).launchEditNoteActivity(noteToEdit, position);
                } else {
                    ((Activity) view.getContext()).startActivityForResult(
                            intent, EDIT_NOTE_REQUEST_CODE);
                }

                return true;
            } else if (id == R.id.action_delete) {
                Toast.makeText(view.getContext(), "Delete note", Toast.LENGTH_SHORT).show();
                notes.remove(position);
                notifyItemRemoved(position);

                if (view.getContext() instanceof MainActivity) {
                    ((MainActivity) view.getContext()).saveNotes();
                }
                return true;
            }
            return false;
        });
        popup.show();
    }


    @Override
    public int getItemCount() {
        return notes != null ? notes.size() : 0;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
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