package com.example.salon_de_eventos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.fragments.EventosFragment;
import com.example.fragments.PerfilFragment;
import com.example.fragments.UsuariosFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity2 extends AppCompatActivity {

    FrameLayout frameLayout;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        frameLayout=(FrameLayout) findViewById(R.id.container_frgaments);
        Snackbar snackbar = Snackbar.make(frameLayout, "Hola, Bienvenido "+bundle.getString("usuario").toString(), Snackbar.LENGTH_LONG);
        View viewSnack = snackbar.getView();
        FrameLayout.LayoutParams params=(FrameLayout.LayoutParams) viewSnack.getLayoutParams();
        params.gravity = Gravity.TOP;
        viewSnack.setLayoutParams(params);
        snackbar.show();
        BottomNavigationView bottomBar=findViewById(R.id.bottom_navigation);
        bottomBar.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.eventos:
                    EventosFragment eventosFragment=new EventosFragment();
                    eventosFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_frgaments,eventosFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                    break;
                case R.id.perfil:
                    PerfilFragment perfilFragment=new PerfilFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_frgaments,perfilFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                    break;
                case R.id.usuarios:
                    UsuariosFragment usuariosFragment=new UsuariosFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_frgaments,usuariosFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                    break;
            }
            return true;
        });
        bottomBar.setSelectedItemId(R.id.eventos);
    }
}