package com.example.educat.RevisionGames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.educat.Home;
import com.example.educat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RevisionGameMenu extends AppCompatActivity {

    FloatingActionButton btnmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision_game_menu1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button button = findViewById(R.id.Hardware);
        final Button button2 = findViewById(R.id.Threats);
        btnmenu = (FloatingActionButton)findViewById(R.id.btnMainMenu);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(RevisionGameMenu.this, HardwareGame.class);
                startActivity(i);
                ((Activity) RevisionGameMenu.this).overridePendingTransition(0, 0);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(RevisionGameMenu.this, ThreatsGame.class);
                startActivity(i);
                ((Activity) RevisionGameMenu.this).overridePendingTransition(0, 0);
            }
        });
        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(RevisionGameMenu.this, Home.class);
                startActivity(menuIntent);
            }
        });
    }
}
