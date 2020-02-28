package com.example.educat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educat.Interface.ItemClickListener;
import com.example.educat.Model.Category;
import com.example.educat.ViewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class RevisionSubCat  extends AppCompatActivity
{
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference subCat;
    String categoryId="";
    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcatlayout);

        database = FirebaseDatabase.getInstance();
        subCat = database.getReference("Subcategory");
        recyclerView = (RecyclerView)findViewById(R.id.subcat);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //gets intent here

        if (getIntent()!= null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if(categoryId != null && !categoryId.isEmpty())
        {
            loadSubCat(categoryId);
        }
    }

    private void loadSubCat(String categoryId)
    {   //fetches foods by child based on id system
        adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(Category.class,R.layout.category_layout,CategoryViewHolder.class,subCat.orderByChild("CategoryId").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(CategoryViewHolder categoryViewHolder, Category category, int position) {
                categoryViewHolder.category_name.setText(category.getName());
                Picasso.with(getBaseContext()).load(category.getImage())
                        .into(categoryViewHolder.category_image);
                final Category local = category;
                categoryViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Starts the new activity for the details of the food
                        Intent subcategory = new Intent (RevisionSubCat.this,Start.class);
                        subcategory.putExtra("CategoryId",adapter.getRef(position).getKey()); //sends to the new activity
                        startActivity(subcategory);

                    }
                });

            }
        };
        //Sets the adapter
        recyclerView.setAdapter(adapter);
    }
}

