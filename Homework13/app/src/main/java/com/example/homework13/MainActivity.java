package com.example.homework13;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.homework13.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "notes_prefs";
    private static final String NOTES_KEY = "notes";

    private SharedPreferences sharedPrefs;
    private Gson gson;  // Библиотека GSON для конвертирования объектов JSON в Java-объекты и наоборот
    private ActivityMainBinding binding;
    private NotesAdapter adapter;
    private List<Note> notes = new ArrayList<>();
    private ActivityResultLauncher<Intent> createNoteLauncher;
    private ActivityResultLauncher<Intent> editNoteLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.notesRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotesAdapter(notes);

        binding.notesRecyclerview.setAdapter(adapter);
        gson = new Gson();
        sharedPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        loadNotes();

        createNoteLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Note newNote = (Note) data.getSerializableExtra("new_note");
                            if (newNote != null) {
                                saveNote(newNote);
                            }
                        }
                    }
                }
        );

        editNoteLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            int position = data.getIntExtra("note_position", -1);
                            Note editedNote = (Note) data.getSerializableExtra("edited_note");
                            if (position != -1 && editedNote != null) {
                                notes.set(position, editedNote);
                                adapter.notifyItemChanged(position);
                                saveNotes();
                            }
                        }
                    }
                });

        binding.fabAddNote.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreateNoteActivity.class);
            createNoteLauncher.launch(intent);
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    public void saveNote(Note note) {
        notes.add(note);
        adapter.notifyItemInserted(notes.size() - 1);
        saveNotes(); // Save list in SharedPreferences
    }

    private void loadNotes() {
        String json = sharedPrefs.getString(NOTES_KEY, null); //return json with all notes
        Type type = new TypeToken<List<Note>>() {
        }.getType();
        notes = gson.fromJson(json, type);  //json -> gson (list)
        if (notes == null) {
            notes = new ArrayList<>();
        }
        adapter.setNotes(notes);
    }

    public void saveNotes() {
        String json = gson.toJson(notes);
        sharedPrefs.edit()
                .putString(NOTES_KEY, json)
                .apply();
    }

    public void launchEditNoteActivity(Note note, int position) {

        Intent intent = new Intent(this, CreateNoteActivity.class);
        intent.putExtra("note", note);
        intent.putExtra("note_position", position);

        editNoteLauncher.launch(intent);  //launch CreateNoteActivity
    }
}
