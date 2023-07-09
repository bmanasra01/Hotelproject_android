package com.example.hotelproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity {
    private Button bookroom_button;
    private Button bookroom_button2;

    private Button reservations_button;
    private Button information_button;
    private Button logout_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bookroom_button = findViewById(R.id.bookroom);
        bookroom_button2 = findViewById(R.id.bookroom2);
        reservations_button = findViewById(R.id.reservations);
        information_button = findViewById(R.id.information);
        logout_button = findViewById(R.id.logoutbutton);

        bookroom_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BookingRoom2.class);
                startActivity(intent);


            }
        });



    }
}