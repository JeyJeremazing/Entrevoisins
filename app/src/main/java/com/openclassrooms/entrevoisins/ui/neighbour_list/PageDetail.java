package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

public class PageDetail extends AppCompatActivity {


    private NeighbourApiService mApiService;
    private Neighbour neighbour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_detail);
        this.configureToolbar();

        ImageView userAvatar = findViewById(R.id.avatarImageView);
        TextView userName = findViewById(R.id.nameText);
        TextView userAddress = findViewById(R.id.addressText);
        TextView userPhone = findViewById(R.id.phoneNumberText);
        TextView aboutMe = findViewById(R.id.aboutMeView);



        long id = getIntent().getLongExtra("index", 0);
        mApiService = DI.getNeighbourApiService();

       neighbour = mApiService.getNeighbour(id);



        userName.setText(neighbour.getName());
        userAddress.setText(neighbour.getAddress());
        userPhone.setText(neighbour.getPhoneNumber());
        aboutMe.setText(neighbour.getAboutMe());
        setButtonState(neighbour);


        //Internet Data avatarUrl:
        Glide.with(this).load(neighbour.getAvatarUrl())
                .centerCrop()
                .into(userAvatar);

        //Favourite button :
        FloatingActionButton neighbourFavourite = findViewById(R.id.floatingActionButton);
        neighbourFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiService.changeFavourite(neighbour);
                setButtonState(neighbour);
            }
        });
    }


    private void configureToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }



    private void setButtonState(Neighbour neighbour) {
        ImageView buttonOn = (ImageView) findViewById(R.id.floatingActionButton);
        buttonOn.setImageResource(neighbour.isFavourite() ? R.drawable.ic_star_white_24dp : R.drawable.ic_star_border_white_24dp);
    }
}