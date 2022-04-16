package com.devbilly.notekeeper.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devbilly.notekeeper.R;
import com.devbilly.notekeeper.databinding.FragmentNotesListBinding;
import com.devbilly.notekeeper.fragments.NotesListFragmentDirections;
import com.devbilly.notekeeper.model.Note;
import com.google.android.material.card.MaterialCardView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NoteListAdapter extends ListAdapter<Note, NoteListAdapter.NoteListViewHolder> {
    private final NavController navController;
    private final NotesListFragmentDirections.NotesAddAction action;
    private final FragmentNotesListBinding binding;


    public NoteListAdapter(@NonNull DiffUtil.ItemCallback<Note> diffCallback, NavController navController, NotesListFragmentDirections.NotesAddAction action, FragmentNotesListBinding binding) {
        super(diffCallback);
        this.navController = navController;
        this.action = action;
        this.binding = binding;
    }


    public static class NoteDiff extends DiffUtil.ItemCallback<Note> {

        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }

    @NonNull
    @Override
    public NoteListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListViewHolder holder, int position) {
        Note current = getItem(position);
        holder.tv_noteTitle.setText(current.getTitle());
        holder.tv_updateDate.setText(current.getUpdateDate());

        holder.note_card.setOnClickListener(v -> {
            action.setNOTE(current);
            navController.navigate(action);
        });

        if (getItemCount() > 0){
            binding.rvNotesList.setVisibility(View.VISIBLE);
            binding.ivEmptyState.setVisibility(View.GONE);
            binding.tvEmptyState.setVisibility(View.GONE);
        }

    }



    public static class NoteListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_noteTitle;
        TextView tv_updateDate;
        MaterialCardView note_card;

        public NoteListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_noteTitle = itemView.findViewById(R.id.note_title);
            tv_updateDate = itemView.findViewById(R.id.tv_edit_date);
            note_card = itemView.findViewById(R.id.note_card);
        }
    }
}
