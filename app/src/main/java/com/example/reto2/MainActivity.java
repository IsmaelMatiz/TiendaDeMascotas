package com.example.reto2;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.reto2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    Button boton1;
    TextView texto1, textoInfoTienda;
    int estadoVsisble;
    LinearLayout layoutTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Pronto podra dejar un mensaje con este boton", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        {
            //Inicializacion de los botones o conexion
            boton1 = (Button) findViewById(R.id.boton1);
            textoInfoTienda = (TextView) findViewById(R.id.textoInfoTienda);
            estadoVsisble = 0;
            texto1 = (TextView) findViewById(R.id.texto1);
            layoutTelefono = (LinearLayout) findViewById(R.id.layoutTelefono);
            //metodo adicional para ocultar un textview
            boton1.setText("Mostrar informacion");
            textoInfoTienda.setVisibility(View.INVISIBLE);
            texto1.setVisibility(View.INVISIBLE);
            layoutTelefono.setVisibility(View.INVISIBLE);
        }
        {
            //metodo que ejecutan los botones
            boton1.setOnClickListener(new View.OnClickListener() { //Metodo clase anonimas
                @Override
                public void onClick(View v) {
                    if (estadoVsisble == 0) {
                        boton1.setText("Ocultar informacion");
                        textoInfoTienda.setVisibility(View.VISIBLE);
                        texto1.setVisibility(View.VISIBLE);
                        layoutTelefono.setVisibility(View.VISIBLE);
                        //toast permite mostrar mensajes como en un joption pain,
                        Toast.makeText(getApplicationContext(), "Se ha mostrado la informacion de la tienda", Toast.LENGTH_LONG).show();
                        estadoVsisble = 1;
                    }else {
                        textoInfoTienda.setVisibility(View.INVISIBLE);
                        texto1.setVisibility(View.INVISIBLE);
                        layoutTelefono.setVisibility(View.INVISIBLE);
                        boton1.setText("Mostrar informacion");
                        estadoVsisble =0;
                        Toast.makeText(getApplicationContext(), "Se ha ocultado la informacion de la tienda", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        //inicializacion del icono de la barra de tareas
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_productos, R.id.nav_servicios, R.id.nav_sucursales).setOpenableLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    protected void onStart() {
        boton1.setOnClickListener(new View.OnClickListener() { //Metodo clase anonimas
            @Override
            public void onClick(View v) {
                if (estadoVsisble == 0) {
                    boton1.setText("Ocultar informacion");
                    textoInfoTienda.setVisibility(View.VISIBLE);
                    texto1.setVisibility(View.VISIBLE);
                    layoutTelefono.setVisibility(View.VISIBLE);
                    //toast permite mostrar mensajes como en un joption pain,
                    Toast.makeText(getApplicationContext(), "Se ha mostrado la informacion de la tienda", Toast.LENGTH_LONG).show();
                    estadoVsisble = 1;
                }else {
                    textoInfoTienda.setVisibility(View.INVISIBLE);
                    texto1.setVisibility(View.INVISIBLE);
                    layoutTelefono.setVisibility(View.INVISIBLE);
                    boton1.setText("Mostrar informacion");
                    estadoVsisble =0;
                    Toast.makeText(getApplicationContext(), "Se ha ocultado la informacion de la tienda", Toast.LENGTH_LONG).show();
                }
            }
        });
        super.onStart();
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemFavoritos:
                Toast.makeText(this, "La estrella amarilla significa que ustéd ha marcado el producto como favorito", Toast.LENGTH_SHORT).show();
                return true;
        }
        switch (item.getItemId()){
            case R.id.itemPregunta:
                Toast.makeText(this, "Para actualizar las listas deslice hacia abajo", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}