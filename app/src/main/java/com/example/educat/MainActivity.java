package com.example.educat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.educat.Common.Common;
import com.example.educat.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {
    MaterialEditText edtNewUser, edtNewPassword, edtNewEmail; //This is for sign up
    MaterialEditText edtUser,edtPassword; //This is for the sign in process

    Button btnSignUp, btnSignIn;
    FirebaseDatabase database;
    DatabaseReference users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Google Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("User");
        edtUser = (MaterialEditText)findViewById(R.id.edtUser) ;
        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);

        btnSignIn = (Button)findViewById(R.id.btn_sign_in);
        btnSignUp = (Button)findViewById(R.id.btn_sign_up);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignUpDialog();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin(edtUser.getText().toString(),edtPassword.getText().toString());
            }
        });
    }

    private void signin(final String user, final String pwd) {
    users.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.child(user).exists())
            {
               if (!user.isEmpty())
               {
                   Users login = dataSnapshot.child(user).getValue(Users.class);
                   if(login.getPassword().equals(pwd)) {
                       Intent homeActivity = new Intent(MainActivity.this, Home.class);
                       Common.currentUser = login;
                       startActivity(homeActivity);
                       finish();
                   }
                   else
                       Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
               }
               else
                   Toast.makeText(MainActivity.this, "Please enter your username", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(MainActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
    }

    private void showSignUpDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle ("Sign Up");
        alertDialog.setMessage("Please enter all required information");

        LayoutInflater inflator = this.getLayoutInflater();
        View registration_layout = inflator.inflate (R.layout.registration_layout,null);

        edtNewUser = (MaterialEditText) registration_layout.findViewById(R.id.edtNewUserName);
        edtNewPassword = (MaterialEditText) registration_layout.findViewById(R.id.edtNewPassword);
        edtNewEmail = (MaterialEditText) registration_layout.findViewById(R.id.edtNewEmail);

        alertDialog.setView(registration_layout);
        alertDialog.setIcon(R.drawable.ic_account_box_black_24dp);

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            }
        });
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                final Users user = new Users (edtNewUser.getText().toString(),
                        edtNewPassword.getText().toString(),
                        edtNewEmail.getText().toString());

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user.getUserName()).exists())
                            Toast.makeText(MainActivity.this, "User Already exits", Toast.LENGTH_SHORT).show();
                        else
                        {
                            users.child(user.getUserName())
                                    .setValue(user);
                            Toast.makeText(MainActivity.this,"User registration success",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();

    }
}
