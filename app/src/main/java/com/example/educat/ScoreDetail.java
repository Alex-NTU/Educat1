package com.example.educat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educat.Model.QuestionScore;
import com.example.educat.ViewHolder.ScoreDetailViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScoreDetail extends AppCompatActivity {

    String viewUser="";
    FirebaseDatabase database;
    DatabaseReference question_score;
    RecyclerView scoreList;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<QuestionScore, ScoreDetailViewHolder>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_detail);

        database = FirebaseDatabase.getInstance();
        question_score = database.getReference("Question_Score");
        scoreList = (RecyclerView)findViewById(R.id.scoreList);
        scoreList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        scoreList.setLayoutManager(layoutManager);

        if (getIntent()!=null)
            viewUser = getIntent().getStringExtra("viewUser");
        if (!viewUser.isEmpty())
            loadScoreDetail(viewUser);
    }

    private void loadScoreDetail(final String viewUser)
    {
        adapter= new FirebaseRecyclerAdapter<QuestionScore, ScoreDetailViewHolder>(
                QuestionScore.class,
                R.layout.score_detail__layout,
                ScoreDetailViewHolder.class, question_score.orderByChild("user").equalTo(viewUser)
        ){
            @Override
            protected void populateViewHolder(ScoreDetailViewHolder scoreDetailViewHolder, QuestionScore questionScore, int i) {
                scoreDetailViewHolder.txt_name.setText(questionScore.getCategoryName());
                scoreDetailViewHolder.txt_score.setText(questionScore.getScore());
            }
        };
        adapter.notifyDataSetChanged();
        scoreList.setAdapter(adapter);
    }
}
