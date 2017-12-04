package com.marttech.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity{
    TextView nameOfMovie,plotSynopsis,userRating, releaseDate;
    ImageView imageView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_detail );
         Toolbar toolbar  =findViewById( R.id.toolbar );
         setSupportActionBar(toolbar);

         getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        initCollapsingToolbar();

        imageView = findViewById(R.id.thumbnail_image_header);
        nameOfMovie = findViewById(R.id.title);
        plotSynopsis = findViewById(R.id.plotsynopsis);
        userRating = findViewById(R.id.userrating);
        releaseDate = findViewById(R.id.releasedate);

        Intent startActivity = getIntent();
        if (startActivity.hasExtra("original_title")){

            String thumbnail = getIntent().getExtras().getString("poster_path");
            String movieName = getIntent().getExtras().getString("original_title");
            String synopsis = getIntent().getExtras().getString("overview");
            String rating = getIntent().getExtras().getString("vote_average");
            String dateOfRelease = getIntent().getExtras().getString("release_date");


            Glide.with(this)
                    .load(thumbnail)
//                    .placeholder(R.drawable.load)
                    .into(imageView);

            nameOfMovie.setText(movieName);
            plotSynopsis.setText(synopsis);
            userRating.setText(rating);
            releaseDate.setText(dateOfRelease);

        }else{
            Toast.makeText( this, "NO API DATA", Toast.LENGTH_SHORT ).show();
        }

    }

    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = findViewById( R.id.appbar );
        appBarLayout.setExpanded(true);


        appBarLayout.addOnOffsetChangedListener( new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0){
                    collapsingToolbarLayout.setTitle( getString( R.string.movie_details ) );
                    isShow = true;
                }else if (isShow){
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }

            }
        } );

    }

}
