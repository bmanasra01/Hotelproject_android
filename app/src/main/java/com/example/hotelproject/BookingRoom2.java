package com.example.hotelproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BookingRoom2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RoomAdapter2 roomAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_room2);

        recyclerView = findViewById(R.id.recycler_view2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve the room data from the web server
        fetchRoomData();
    }

    private void fetchRoomData() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://192.168.218.2/hotelproject/get_room_data.php")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();

                    // Parse the JSON response using Gson
                    Gson gson = new Gson();
                    Type roomListType = new TypeToken<List<Room>>() {}.getType();
                    List<Room> roomList = gson.fromJson(responseBody, roomListType);

                    // Update the UI on the main thread
                    runOnUiThread(() -> {
                        roomAdapter2 = new RoomAdapter2(roomList);
                        recyclerView.setAdapter(roomAdapter2);
                    });
                }
            }
        });
    }
}
