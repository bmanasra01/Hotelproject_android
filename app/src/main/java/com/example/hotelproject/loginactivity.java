package com.example.hotelproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class loginactivity extends AppCompatActivity {
    private EditText editTextEmaill;
    private EditText editTextPasswordl;

    private static final String LOGIN_URL = "http://192.168.218.2/hotelproject/login.php"; // Replace with your login PHP file URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        editTextEmaill = findViewById(R.id.editTextEmail);
        editTextPasswordl = findViewById(R.id.editTextPassword);

        Button buttonLogin = findViewById(R.id.buttonLogin);


        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        String storedEmail = sharedPreferences.getString("email", "");
        String storedPassword = sharedPreferences.getString("password", "");
        editTextEmaill.setText(storedEmail);
        editTextPasswordl.setText(storedPassword);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmaill.getText().toString();
                String password = editTextPasswordl.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(loginactivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Make a request to the login PHP script
                loginRequest(email, password);
            }
        });
    }

    public void goToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void loginRequest(String email, String password) {
        // Create a request using Volley library
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the response from the PHP script
                        if (response.equals("success")) {
                            // If login is successful, navigate to the next activity
                            Intent intent = new Intent(loginactivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish(); // Optional: Prevents going back to the login activity
                        } else {
                            // If login is unsuccessful, display an error message
                            Toast.makeText(loginactivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                        }
                        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email", email);
                        editor.putString("password", password);
                        editor.apply();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error response
                        Toast.makeText(loginactivity.this, "Error occurred: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Parameters to be sent in the POST request
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        // Add the request to the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
