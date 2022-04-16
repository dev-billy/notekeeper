package com.devbilly.notekeeper.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devbilly.notekeeper.R;
import com.devbilly.notekeeper.databinding.FragmentNotesAddBinding;
import com.devbilly.notekeeper.model.Note;
import com.devbilly.notekeeper.viewModel.NotesViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;


public class NotesAddFragment extends Fragment {

    private FragmentNotesAddBinding binding;
    private Note savedNote;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNotesAddBinding.inflate(inflater,container,false);
        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fabDeleteNote.setVisibility(View.GONE);
        savedNote = NotesAddFragmentArgs.fromBundle(getArguments()).getNOTE();
        if(savedNote != null) {
            String savedNoteTitle = savedNote.getTitle();
            String savedNoteValue = savedNote.getNotesValue();
            binding.etTitle.setText(savedNoteTitle);
            binding.etNotes.setText(savedNoteValue);
            binding.fabDeleteNote.setVisibility(View.VISIBLE);
        }
        binding.fabDeleteNote.setOnClickListener(v -> {
            deleteNote(savedNote);
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    public void saveNote(){
        NotesViewModel mNotesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);
        mNotesViewModel.addNote(new Note(binding.etTitle.getText().toString(),"12/02/2032",binding.etNotes.getText().toString()));
        Toast.makeText(getContext(),"Saving Note", Toast.LENGTH_SHORT).show();
        NavController navController = NavHostFragment.findNavController(NotesAddFragment.this);
        navController.navigate(R.id.noteListSaveAction);
    }

    public void updateNote(Note note){
        NotesViewModel mNotesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);
        note.setTitle(binding.etTitle.getText().toString());
        note.setNotesValue(binding.etNotes.getText().toString());
        mNotesViewModel.updateNote(note);
        Toast.makeText(getContext(),"Updating Note", Toast.LENGTH_SHORT).show();
        NavController navController = NavHostFragment.findNavController(NotesAddFragment.this);
        navController.navigate(R.id.noteListSaveAction);
    }

    public void deleteNote(Note note){
        NotesViewModel mNotesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);
        mNotesViewModel.deleteNote(note);
        Toast.makeText(getContext(),"Deleting Note", Toast.LENGTH_SHORT).show();
        NavController navController = NavHostFragment.findNavController(NotesAddFragment.this);
        navController.navigate(R.id.noteListSaveAction);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.save_note){
            if(savedNote != null ){
                updateNote(savedNote);
            }else{
                saveNote();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}