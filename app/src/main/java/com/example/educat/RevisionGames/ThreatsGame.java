package com.example.educat.RevisionGames;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.educat.Home;
import com.example.educat.R;

import java.util.Arrays;
import java.util.Collections;

public class ThreatsGame extends AppCompatActivity implements View.OnClickListener {

    ImageView Box1, Box2, Box3, Box4, Box5, Box6, Box7, Box8, Box9, Box10, Box11, Box12;
    TextView Instruction;

    // array for the images
    Integer[] cards = {101,102,103,104,105,106,201,202,203,204,205,206};
    // Actual images
    int phishingImage, socialImage, spywareImage, virusImage, wormImage, trojanImage, phishingImage1, socialImage1, spywareImage1, virusImage1, wormImage1, trojanImage1;

    int card1, card2;
    int first, second;

    int cardNumber = 1;
    int turn = 1;
    int playerPoints = 0, cpuPoints = 0;
    boolean pause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revision_network);
        Instruction = (TextView)findViewById(R.id.instruction);
        Instruction.setText("Find the pieces of hardware and match them to their description");

        Box1 = (ImageView) findViewById(R.id.box1);
        Box2 = (ImageView) findViewById(R.id.box2);
        Box3 = (ImageView) findViewById(R.id.box3);
        Box4 = (ImageView) findViewById(R.id.box4);
        Box5 = (ImageView) findViewById(R.id.box5);
        Box6 = (ImageView) findViewById(R.id.box6);
        Box7 = (ImageView) findViewById(R.id.box7);
        Box8 = (ImageView) findViewById(R.id.box8);
        Box9 = (ImageView) findViewById(R.id.box9);
        Box10 = (ImageView) findViewById(R.id.box10);
        Box11 = (ImageView) findViewById(R.id.box11);
        Box12 = (ImageView) findViewById(R.id.box12);

        Box1.setTag("0");
        Box2.setTag("1");
        Box3.setTag("2");
        Box4.setTag("3");
        Box5.setTag("4");
        Box6.setTag("5");
        Box7.setTag("6");
        Box8.setTag("7");
        Box9.setTag("8");
        Box10.setTag("9");
        Box11.setTag("10");
        Box12.setTag("11");
        // load the card images
        frontOfCardsResources();

        // Shuffle the images
        Collections.shuffle(Arrays.asList(cards));

        //changing the color of the second player to display inactivity

        Box1.setOnClickListener(this);
        Box2.setOnClickListener(this);
        Box3.setOnClickListener(this);
        Box4.setOnClickListener(this);
        Box5.setOnClickListener(this);
        Box6.setOnClickListener(this);
        Box7.setOnClickListener(this);
        Box8.setOnClickListener(this);
        Box9.setOnClickListener(this);
        Box10.setOnClickListener(this);
        Box11.setOnClickListener(this);
        Box12.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int theCard;

        switch (view.getId()) {
            case R.id.box1:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(Box1, theCard);
                break;
            case R.id.box2:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(Box2, theCard);
                break;
            case R.id.box3:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(Box3, theCard);
                break;
            case R.id.box4:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(Box4, theCard);
                break;
            case R.id.box5:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(Box5, theCard);
                break;
            case R.id.box6:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(Box6, theCard);
                break;

            case R.id.box7:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(Box7, theCard);
                break;
            case R.id.box8:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(Box8, theCard);
                break;

            case R.id.box9:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(Box9, theCard);
                break;

            case R.id.box10:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(Box10, theCard);
                break;
            case R.id.box11:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(Box11, theCard);
                break;
            case R.id.box12:
                pause = true;
                theCard = Integer.parseInt((String) view.getTag());
                doStuff(Box12, theCard);
                break;
        }
    }

    private void doStuff(ImageView iv, int card) {

        // set the correct image to the imageview
        switch (cards[card]) {
            case 101:
                iv.setImageResource(phishingImage);
                break;
            case 102:
                iv.setImageResource(socialImage);
                break;
            case 103:
                iv.setImageResource(spywareImage);
                break;
            case 104:
                iv.setImageResource(virusImage);
                break;
            case 105:
                iv.setImageResource(wormImage);
                break;
            case 106:
                iv.setImageResource(trojanImage1);
                break;
            case 201:
                iv.setImageResource(phishingImage1);
                break;
            case 202:
                iv.setImageResource(socialImage1);
                break;
            case 203:
                iv.setImageResource(spywareImage1);
                break;
            case 204:
                iv.setImageResource(virusImage1);
                break;
            case 205:
                iv.setImageResource(wormImage1);
                break;
            case 206:
                iv.setImageResource(trojanImage1);
                break;
        }

        // check which image is selected and save it temporary
        if (cardNumber == 1) {

            card1 = cards[card];
            if (card1 > 200) {
                card1 = card1 - 100;
            }
            cardNumber = 2;
            first = card;

            iv.setEnabled(false);

        } else if (cardNumber == 2) {
            card2 = cards[card];
            if (card2 > 200) {
                card2 = card2 - 100;
            }
            cardNumber = 1;
            second = card;

            Box1.setEnabled(false);
            Box2.setEnabled(false);
            Box3.setEnabled(false);
            Box4.setEnabled(false);
            Box5.setEnabled(false);
            Box6.setEnabled(false);
            Box7.setEnabled(false);
            Box8.setEnabled(false);
            Box9.setEnabled(false);
            Box10.setEnabled(false);
            Box11.setEnabled(false);
            Box12.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    // check if the selected images are equal
                    calculate();
                }
            }, 1000);
        }

        Handler delayHandler = new Handler();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                // check if the selected images are equal
                pause = false;
            }
        }, 1000);

    }

    private void calculate() {
        // if images are equal remove them and add point.
        if(card1 == card2){
            if(first == 0) {
                Box1.setVisibility(View.INVISIBLE);
            }else if (first == 1){
                Box2.setVisibility(View.INVISIBLE);
            }else if (first == 2){
                Box3.setVisibility(View.INVISIBLE);
            }else if (first == 3){
                Box4.setVisibility(View.INVISIBLE);
            }else if (first == 4){
                Box5.setVisibility(View.INVISIBLE);
            }else if (first == 5){
                Box6.setVisibility(View.INVISIBLE);
            }else if (first == 6){
                Box7.setVisibility(View.INVISIBLE);
            }else if (first == 7){
                Box8.setVisibility(View.INVISIBLE);
            }else if (first == 8){
                Box9.setVisibility(View.INVISIBLE);
            }else if (first == 9){
                Box10.setVisibility(View.INVISIBLE);
            }else if (first == 10) {
                Box11.setVisibility(View.INVISIBLE);
            }else if (first == 11) {
                Box12.setVisibility(View.INVISIBLE);
            }

            if(second == 0) {
                Box1.setVisibility(View.INVISIBLE);
            }else if (second == 1){
                Box2.setVisibility(View.INVISIBLE);
            }else if (second == 2){
                Box3.setVisibility(View.INVISIBLE);
            }else if (second == 3){
                Box4.setVisibility(View.INVISIBLE);
            }else if (second == 4){
                Box5.setVisibility(View.INVISIBLE);
            }else if (second == 5){
                Box6.setVisibility(View.INVISIBLE);
            }else if (second == 6){
                Box7.setVisibility(View.INVISIBLE);
            }else if (second == 7){
                Box8.setVisibility(View.INVISIBLE);
            }else if (second == 8){
                Box9.setVisibility(View.INVISIBLE);
            }else if (second == 9){
                Box10.setVisibility(View.INVISIBLE);
            }else if (second == 10) {
                Box11.setVisibility(View.INVISIBLE);
            }else if (second == 11) {
                Box12.setVisibility(View.INVISIBLE);
            }


        }else{
            Box1.setImageResource(R.drawable.cardback);
            Box2.setImageResource(R.drawable.cardback);
            Box3.setImageResource(R.drawable.cardback);
            Box4.setImageResource(R.drawable.cardback);
            Box5.setImageResource(R.drawable.cardback);
            Box6.setImageResource(R.drawable.cardback);
            Box7.setImageResource(R.drawable.cardback);
            Box8.setImageResource(R.drawable.cardback);
            Box9.setImageResource(R.drawable.cardback);
            Box10.setImageResource(R.drawable.cardback);
            Box11.setImageResource(R.drawable.cardback);
            Box12.setImageResource(R.drawable.cardback);

        }

        Box1.setEnabled(true);
        Box2.setEnabled(true);
        Box3.setEnabled(true);
        Box4.setEnabled(true);
        Box5.setEnabled(true);
        Box6.setEnabled(true);
        Box7.setEnabled(true);
        Box8.setEnabled(true);
        Box9.setEnabled(true);
        Box10.setEnabled(true);
        Box11.setEnabled(true);
        Box12.setEnabled(true);

        // check if the game is over
        checkEnd();
    }

    private void checkEnd() {
        if(Box1.getVisibility() == View.INVISIBLE &&
                Box2.getVisibility() == View.INVISIBLE &&
                Box3.getVisibility() == View.INVISIBLE &&
                Box4.getVisibility() == View.INVISIBLE &&
                Box5.getVisibility() == View.INVISIBLE &&
                Box6.getVisibility() == View.INVISIBLE &&
                Box7.getVisibility() == View.INVISIBLE &&
                Box8.getVisibility() == View.INVISIBLE &&
                Box9.getVisibility() == View.INVISIBLE &&
                Box10.getVisibility() == View.INVISIBLE &&
                Box11.getVisibility() == View.INVISIBLE &&
                Box12.getVisibility() == View.INVISIBLE){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ThreatsGame.this);
            alertDialogBuilder
                    .setPositiveButton("Return To Game Menu", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), RevisionGameMenu.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("Return to menu", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            startActivity(intent);
                            finish();

                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
    private void frontOfCardsResources() {
        phishingImage = R.drawable.phishing;
        socialImage = R.drawable.social;
        spywareImage = R.drawable.spyware;
        virusImage = R.drawable.virus;
        wormImage = R.drawable.worm;
        trojanImage = R.drawable.trojan;
        phishingImage1 = R.drawable.phishing1;
        socialImage1 = R.drawable.social1;
        spywareImage1 = R.drawable.spyware1;
        virusImage1 = R.drawable.virus1;
        wormImage1 = R.drawable.worm1;
        trojanImage = R.drawable.trojan1;
    }
}
