package com.example.educat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.educat.Notes.NoteMainActivity;
import com.example.educat.Revision.RevisionMainActivity;
import com.example.educat.RevisionGames.RevisionGameMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId())
                {
                    case R.id.action_category:
                        selectedFragment = CategoryFragment.newInstance();
                        break;
                    case R.id.action_ranking:
                        selectedFragment = RankingFragment.newInstance();
                        break;
                    case R.id.action_revision:
                        selectedFragment = RevisionFragment.newInstance();
                        moveToNewActivityRevision ();
                        break;
                    case R.id.RevisionGame:
                        selectedFragment = CategoryFragment.newInstance();
                        moveToNewActivityRevisionGame();
                        break;
                    case R.id.action_diary:
                        selectedFragment = CategoryFragment.newInstance();
                        moveToNewActivityNotes();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout,selectedFragment);
                transaction.commit();
                return true;
            }
        });
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout,CategoryFragment.newInstance());
        transaction.commit();
    }

    private void moveToNewActivityNotes () {

       Intent i = new Intent(Home.this, NoteMainActivity.class);
        startActivity(i);
        ((Activity) Home.this).overridePendingTransition(0, 0);
    }
    private void moveToNewActivityRevision () {

        Intent i = new Intent(Home.this, RevisionMainActivity.class);
        startActivity(i);
        ((Activity) Home.this).overridePendingTransition(0, 0);
    }
    private void moveToNewActivityRevisionGame () {

        Intent i = new Intent(Home.this, RevisionGameMenu.class);
        startActivity(i);
        ((Activity) Home.this).overridePendingTransition(0, 0);
    }
}
