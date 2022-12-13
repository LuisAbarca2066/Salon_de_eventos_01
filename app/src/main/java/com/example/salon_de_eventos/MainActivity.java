package com.example.salon_de_eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDataUsingVolley(username.getText().toString().trim(),password.getText().toString().trim());
                /*
                if (username.getText().toString().equals("user") && password.getText().toString().equals("1234")) {
                    Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
                */

            }
        });
    }
    public void lanzar_Activity2(View vista){

        Intent lanzar1 = new Intent(this,MainActivity2.class);
        startActivity(lanzar1);

    }

    private void postDataUsingVolley(String usernamep, String passwordp) {
        // URL API LOGIN
        String url = "http://192.168.171.41/SalonEventos/public/api/login";
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new com.android.volley.Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                    // inside on response method we are
                    // hiding our progress bar
                    // and setting data to edit text as empty
                        username.setText("");
                        password.setText("");
                        // on below line we are displaying a success toast message.
                        //Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();
                        try {
                            // on below line we are parsing the response
                            // to json object to extract data from it.
                            JSONObject respObj = new JSONObject(response);
                            // below are the strings which we
                            // extract from our json object.
                            String user = respObj.getString("user");
                            String token = respObj.getString("token");
                            // on below line we are setting this string s to our text view4
                            Bundle datos = new Bundle();
                            datos.putString("usuario",user);
                            datos.putString("token",token);
                            Intent lanzar1 = new Intent(MainActivity.this,MainActivity2.class);
                            lanzar1.putExtras(datos);
                            startActivity(lanzar1);
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "ENTRA AL CATCH", Toast.LENGTH_SHORT).show();
                        }
                    }
               }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
                }) {
                @Override
                protected Map<String, String> getParams() {
                    // below line we are creating a map for
                    // storing our values in key and value pair.
                    Map<String, String> params = new HashMap<String, String>();
                    // on below line we are passing our key
                    // and value pair to our parameters.
                    params.put("email", usernamep);
                    params.put("password", passwordp);
                    // at last we are
                    // returning our params.
                    return params;
                }
            };
            // below line is to make
            // a json object request.
            queue.add(request);
        }
}

