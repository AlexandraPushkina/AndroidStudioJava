package com.example.homework13;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.homework13.databinding.ActivityCreateNoteBinding;
import java.util.Random;

public class CreateNoteActivity extends AppCompatActivity {

    private ActivityCreateNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.buttonSave.setOnClickListener(v -> {
            String title = binding.editTextTitle.getText().toString();
            String text = binding.editTextNote.getText().toString();

            if (!title.isEmpty() && !text.isEmpty()) {
                int style = (new Random()).nextInt(4);
                Note note = new Note(title, text, style);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("new_note", note);
                setResult(Activity.RESULT_OK, resultIntent);

                finish();
            } else {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
