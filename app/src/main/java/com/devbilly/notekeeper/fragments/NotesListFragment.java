package com.devbilly.notekeeper.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devbilly.notekeeper.R;
import com.devbilly.notekeeper.adapter.NoteListAdapter;
import com.devbilly.notekeeper.databinding.FragmentNotesListBinding;
import com.devbilly.notekeeper.model.Note;
import com.devbilly.notekeeper.viewModel.NotesViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class NotesListFragment extends Fragment {

    private FragmentNotesListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNotesListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NotesListFragmentDirections.NotesAddAction action = NotesListFragmentDirections.notesAddAction();
        NavController navController = NavHostFragment.findNavController(NotesListFragment.this);
        RecyclerView recyclerView = binding.rvNotesList;
        NoteListAdapter adapter = new NoteListAdapter(new NoteListAdapter.NoteDiff(), navController, action, binding);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        NotesViewModel mNotesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);

        mNotesViewModel.getAllNotes().observe(requireActivity(), adapter::submitList);

        binding.fabAddNote.setOnClickListener(v -> {
            navController.navigate(R.id.notesAddAction);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}