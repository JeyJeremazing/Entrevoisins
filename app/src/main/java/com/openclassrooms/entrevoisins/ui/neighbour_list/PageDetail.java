package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class PageDetail extends AppCompatActivity {
    private NeighbourApiService mApiService;
    private Neighbour neighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_detail);
        this.configureToolbar();
        displayData();
        setFavouriteButton();
    }

    private void setFavouriteButton() {
        FloatingActionButton neighbourFavourite = findViewById(R.id.floatingActionButton);
        neighbourFavourite.setOnClickListener(view -> {
            mApiService.changeFavourite(neighbour);
            setButtonState(neighbour);
        });
    }

    private void displayData() {
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

        Glide.with(this).load(neighbour.getAvatarUrl())
                .centerCrop()
                .into(userAvatar);
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setButtonState(Neighbour neighbour) {
        ImageView buttonOn = findViewById(R.id.floatingActionButton);
        buttonOn.setImageResource(neighbour.isFavourite() ? R.drawable.ic_star_white_24dp : R.drawable.ic_star_border_white_24dp);
    }
}