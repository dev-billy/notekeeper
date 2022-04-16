package com.devbilly.notekeeper.activities;

import android.os.Bundle;
import android.view.View;

import com.devbilly.notekeeper.R;
import com.devbilly.notekeeper.adapter.NoteListAdapter;
import com.devbilly.notekeeper.databinding.ActivityMainBinding;
import com.devbilly.notekeeper.fragments.NotesListFragment;
import com.devbilly.notekeeper.model.Note;
import com.devbilly.notekeeper.viewModel.NotesViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitleTextAppearance(this,R.style.ToolbarStyle);
        binding.toolbar.setTitleTextColor(getResources().getColor(R.color.white));



        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController  navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController,appBarConfiguration) || super.onSupportNavigateUp();
    }
}