package com.example.educat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educat.Common.Common;
import com.example.educat.Interface.ItemClickListener;
import com.example.educat.Interface.RankingCallBack;
import com.example.educat.Model.QuestionScore;
import com.example.educat.Model.Ranking;
import com.example.educat.ViewHolder.RankingViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RankingFragment extends Fragment
{
    View myFragment;
    int sum = 0;
    FirebaseDatabase database;
    DatabaseReference questionScore,rankingTable;
    RecyclerView rankingList;
    LinearLayoutManager layoutManager;
    FirebaseRecyclerAdapter<Ranking, RankingViewHolder>adapter;

    public static RankingFragment newInstance(){
        RankingFragment rankingFragment =new RankingFragment();
        return rankingFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        questionScore = database.getReference("Question_Score");
        rankingTable = database.getReference("Ranking");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_ranking,container,false);

        rankingList = (RecyclerView)myFragment.findViewById(R.id.rankingList);
        layoutManager = new LinearLayoutManager(getActivity());
        rankingList.setHasFixedSize(true);
        layoutManager.setReverseLayout(true); //reverse order so highest score at top
        layoutManager.setStackFromEnd(true);
        rankingList.setLayoutManager(layoutManager);

        updateScore(Common.currentUser.getUserName(), new RankingCallBack<Ranking>() {
            @Override
            public void callback(Ranking ranking) {
                //Updates ranking table
                rankingTable.child(ranking.getUserName())
                        .setValue(ranking);
                //showRanking(); //after saved scores sort table and show results
            }
        });
        adapter = new FirebaseRecyclerAdapter<Ranking, RankingViewHolder>(
                Ranking.class,
                R.layout.layout_rankings,
                RankingViewHolder.class,
                rankingTable.orderByChild("score")
        ){
            protected void populateViewHolder(RankingViewHolder viewHolder, final Ranking model, int position)
            {
              viewHolder.txt_name.setText(model.getUserName());
              viewHolder.txt_score.setText(String.valueOf(model.getScore()));

              viewHolder.setItemClickListener(new ItemClickListener() {
                  @Override
                  public void onClick(View view, int position, boolean isLongClick) {
                      Intent scoreDetail = new Intent(getActivity(),ScoreDetail.class);
                      scoreDetail.putExtra("viewUser",model.getUserName());
                      startActivity(scoreDetail);
                  }
              });
            }
        };
        adapter.notifyDataSetChanged();
        rankingList.setAdapter(adapter);
        return myFragment;
    }



    private void updateScore(final String userName, final RankingCallBack<Ranking>callBack)
    {
        questionScore.orderByChild("user").equalTo(userName)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot data:dataSnapshot.getChildren())
                        {
                            QuestionScore quest = data.getValue(QuestionScore.class);
                            sum += Integer.parseInt(quest.getScore());
                        }
                        //After quiz sum all scores
                        Ranking ranking = new Ranking (userName,sum);
                        callBack.callback(ranking);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
