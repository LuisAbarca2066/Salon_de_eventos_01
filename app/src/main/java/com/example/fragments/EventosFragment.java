package com.example.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.salon_de_eventos.MainActivity;
import com.example.salon_de_eventos.MainActivity2;
import com.example.salon_de_eventos.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EventosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventosFragment newInstance(String param1, String param2) {
        EventosFragment fragment = new EventosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //String token  = getArguments().getString("token");
        //Snackbar snackbar = Snackbar.make(getView(),"Hola mundo", Snackbar.LENGTH_LONG);

        //titulo.setText(token);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View eventosFragment= inflater.inflate(R.layout.fragment_eventos, container, false);
        String token  = getArguments().getString("token");
        getEventos(token);
        return  eventosFragment;
    }

    private void getEventos(String token) {
        // URL API LOGIN
        //TextView titulo=getView().findViewById(R.id.tituloEventos);
        String url = "http://192.168.171.41/SalonEventos/public/api/eventos";
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest request  = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // on below line we are parsing the response
                            // to json object to extract data from it.
                            TextView titulo=getView().findViewById(R.id.tituloEventos);
                            titulo.setText(response.toString());


                        } catch (Exception e) {
                            TextView titulo=getView().findViewById(R.id.tituloEventos);
                            titulo.setText(e.getMessage());
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                TextView titulo=getView().findViewById(R.id.tituloEventos);
                titulo.setText(error.getMessage());
                Toast.makeText(getContext(), "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type","application/json");
                headers.put("Authorization", "Bearer "+token);

                return headers;
            }
        };
        queue.add(request);
    }
}