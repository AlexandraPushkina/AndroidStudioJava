package com.example.homework13;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.homework13.databinding.FragmentCreateNoteBinding;

import java.util.Random;


public class CreateNoteFragment extends DialogFragment {

    private FragmentCreateNoteBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateNoteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSave.setOnClickListener(v -> {

            String title = binding.editTextTitle.getText().toString();
            String text = binding.editTextNote.getText().toString();

            if (!title.isEmpty() && !text.isEmpty()){
                int style = (new Random()).nextInt(4);
                Note note = new Note(title, text, style);

                MainActivity activity = (MainActivity) getActivity();
                if (activity != null) {
                    activity.saveNote(note);
                }
                dismiss();
            } else {
                Toast.makeText(getContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

