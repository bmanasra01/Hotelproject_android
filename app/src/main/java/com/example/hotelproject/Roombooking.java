package com.example.hotelproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Roombooking extends AppCompatActivity {

        private RecyclerView recyclerView;
        private RoomAdapter roomAdapter;
        private List<Room> roomList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_roombooking);

            recyclerView = findViewById(R.id.recyclerViewRooms);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            // Initialize the roomList and roomAdapter
            roomList = new ArrayList<>();
            roomAdapter = new RoomAdapter(roomList);

            recyclerView.setAdapter(roomAdapter);

            // Fetch room data from the database
            fetchRoomData();
        }

    private void fetchRoomData() {
        // Make a request to the database to retrieve the room data
        // Example implementation using Volley library
        String url = "http://192.168.218.2/hotelproject/get_room_data.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Roombooking", "Response: " + response); // Log the response

                        // Parse the response and populate the roomList
                        roomList.clear();
                        roomList.addAll(parseRoomData(response));
                        roomAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error response
                        Toast.makeText(Roombooking.this, "Error occurred: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private List<Room> parseRoomData(String response) {
        // Parse the response JSON and create a list of Room objects
        // Example implementation using JSONObject and JSONArray

        List<Room> roomList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response);
            Log.d("Roombooking", "JSON Array length: " + jsonArray.length()); // Log the array length

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.d("Roombooking", "Room JSON Object: " + jsonObject.toString()); // Log the room JSON object

                String roomNumber = jsonObject.getString("room_number");
                String roomRate = jsonObject.getString("room_rate");
                String roomPrice = jsonObject.getString("room_price");
                int roomCapacity = jsonObject.getInt("room_capacity");
                boolean isReserved = jsonObject.getBoolean("is_reserved");

                Room room = new Room(roomNumber, roomRate, roomPrice, roomCapacity, isReserved);
                roomList.add(room);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("Roombooking", "Parsed Room List: " + roomList.toString()); // Log the parsed room list
        return roomList;
    }
    }